package com.zhengqing.modules.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhengqing.config.Constants;
import com.zhengqing.modules.common.exception.MyException;
import com.zhengqing.modules.system.dto.input.UserQueryPara;
import com.zhengqing.modules.system.dto.model.ButtonVO;
import com.zhengqing.modules.system.dto.model.MenuVO;
import com.zhengqing.modules.system.dto.model.UserInfoVO;
import com.zhengqing.modules.system.entity.Menu;
import com.zhengqing.modules.system.entity.Role;
import com.zhengqing.modules.system.entity.User;
import com.zhengqing.modules.system.repository.RoleMenuRepository;
import com.zhengqing.modules.system.repository.UserRepository;
import com.zhengqing.modules.system.repository.UserRoleRepository;
import com.zhengqing.modules.system.service.IUserService;
import com.zhengqing.utils.PasswordUtils;
import com.zhengqing.utils.TreeBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * <p> System Management-User Basic Information Table Service Implementation Class </p>
 *
 * @author: zhengqing
 * @date: 2019-08-19
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final RoleMenuRepository roleMenuRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public void listPage(Page<User> page, UserQueryPara filter) {
        int pageIndex = Math.max(page.getCurrent() - 1, 0);
        int pageSize = page.getSize();
        page.setRecords(userRepository.selectUsers(PageRequest.of(pageIndex, pageSize), filter));
    }

    @Override
    public List<User> list(UserQueryPara filter) {
        return userRepository.selectUsers(filter);
    }

    @Override
    public UserInfoVO getCurrentUserInfo(String token) {
        User user = userRepository.getUserInfoByToken(token);
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtil.copyProperties(user, userInfoVO);

        Set<String> roles = new HashSet();
        Set<MenuVO> menuVOS = new HashSet();
        Set<ButtonVO> buttonVOS = new HashSet();

        //Query the role of a user
        List<Role> roleList = userRoleRepository.selectRoleByUserId(user.getId());
        if (roleList != null && !roleList.isEmpty()) {
            roles.add(roleList.get(0).getCode());

            //Query the menu of a character
            List<Menu> menuList = roleMenuRepository.selectMenusByRoleId(roleList.get(0).getId());
            if (menuList != null && !menuList.isEmpty()) {
                menuList.stream().filter(Objects::nonNull).forEach(menu -> {
                    if ("button".equals(menu.getType().toLowerCase())) {
                        //If the permission is a button, add it to the button
                        ButtonVO buttonVO = new ButtonVO();
                        BeanUtil.copyProperties(menu, buttonVO);
                        buttonVOS.add(buttonVO);
                    }
                    if ("menu".equals(menu.getType().toLowerCase())) {
                        //If the permission is a menu, add it to the menu
                        MenuVO menuVO = new MenuVO();
                        BeanUtil.copyProperties(menu, menuVO);
                        menuVOS.add(menuVO);
                    }
                });
            }
        }
        userInfoVO.getRoles().addAll(roles);
        userInfoVO.getButtons().addAll(buttonVOS);
        userInfoVO.getMenus().addAll(TreeBuilder.buildTree(menuVOS));
        return userInfoVO;
    }

    @Override
    public org.springframework.data.domain.Page<User> selectUsers(Pageable page, UserQueryPara filter) {
        return userRepository.findAll(UserRepository.buildSpecification(filter), page);
    }

    @Override
    public List<User> selectUsers(UserQueryPara filter) {
        return userRepository.selectUsers(filter);
    }

    @Override
    public User selectUserByUsername(String username) {
        return userRepository.selectUserByUsername(username);
    }

    @Override
    public User getUserInfoByToken(String token) {
        return userRepository.getUserInfoByToken(token);
    }

    @Override
    public User getUserInfoByQQ(String qqOppenId) {
        return userRepository.getUserInfoByQQ(qqOppenId);
    }

    @Override
    public List<User> selectUserByRoleId(Integer roleId) {
        return userRepository.selectUserByRoleId(roleId);
    }

    @Override
    public List<User> selectList(Object o) {
        return Collections.emptyList();
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public User selectById(Integer id) {
        return null;
    }

    @Override
    public Integer save(User para) {
        if (para.getId() != null) {
            User user = userRepository.selectById(para.getId());
            para.setPassword(PasswordUtils.encodePassword(para.getPwd(), user.getSalt()));
//            userRepository.updateById(para);
            userRepository.save(para);
        } else {
            para.setSalt(Constants.SALT);
            para.setPassword(PasswordUtils.encodePassword(para.getPwd(), Constants.SALT));
            userRepository.save(para);
        }
        return para.getId();
    }

    @Override
    public Integer updatePersonalInfo(User para) {
        if (para.getId() == null) {
            throw new MyException("User information has been lost abnormally, please log in again and try to modify your personal information!");
        }
        if (StringUtils.isBlank(para.getUsername())) {
            throw new MyException("The account cannot be empty!");
        }
        if (StringUtils.isBlank(para.getNickName())) {
            throw new MyException("Nickname cannot be empty!");
        }
        User user = userRepository.selectById(para.getId());
        if (StringUtils.isNotBlank(para.getPwd())) {
            if (para.getPwd().trim().length() < 6) {
                throw new MyException("Please set a password of at least 6 digits!");
            }
            // Update password
            para.setPassword(PasswordUtils.encodePassword(para.getPwd(), user.getSalt()));
        } else {
            para.setPwd(null);
        }

        //Verify if the account is a duplicate
        UserQueryPara userQueryPara = new UserQueryPara();
        userQueryPara.setAccount(para.getUsername());
        List<User> userList = userRepository.selectUsers(userQueryPara);
        if (!CollectionUtils.isEmpty(userList)) {
            if (!para.getUsername().equals(user.getUsername()) || userList.size() > 1) {
                throw new MyException("The account is duplicated, please re-enter!");
            }
        }
//        userRepository.updateById(para);
        userRepository.save(para);
        return para.getId();
    }

}
