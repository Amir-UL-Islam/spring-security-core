package com.zhengqing.modules.system.service.impl;

import com.zhengqing.modules.system.dto.input.UserRoleQueryPara;
import com.zhengqing.modules.system.entity.UserRole;
import com.zhengqing.modules.system.repository.UserRoleRepository;
import com.zhengqing.modules.system.service.IUserRoleService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p> System Management - User Role Association Table Service Implementation Class </p>
 *
 * @author: zhengqing
 * @date: 2019-08-20
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserRoleServiceImpl implements IUserRoleService {

    private final UserRoleRepository userRoleRepository;


    @Override
    public Page<UserRole> listPage(Pageable pageable, UserRoleQueryPara filter) {
        return userRoleRepository.selectUserRoles(pageable, filter);
    }

    @Override
    public List<UserRole> list(UserRoleQueryPara filter) {
        return userRoleRepository.selectUserRoles(filter);
    }

    @Override
    public Integer save(UserRole para) {
        if (para.getId() != null) {
//            userRoleMapper.updateById(para);
            userRoleRepository.save(para);
        } else {
//            userRoleMapper.insert(para);
            userRoleRepository.save(para);
        }
        return para.getId();
    }

    @Override
    public void saveUserRole(UserRoleQueryPara para) {
        Integer roleId = para.getRoleId();
        String userIds = para.getUserIds();
//        userRoleMapper.deleteByRoleId(roleId);
        userRoleRepository.deleteByRoleId(roleId);
        if (StringUtils.isNotBlank(userIds)) {
            String[] userIdArrays = userIds.split(",");
            if (userIdArrays.length > 0) {
                for (String userId : userIdArrays) {
                    UserRole userRole = new UserRole();
                    userRole.setRoleId(roleId);
                    userRole.setUserId(Integer.parseInt(userId));
//                    userRoleMapper.insert(userRole);
                    userRoleRepository.save(userRole);
                }
            }
        }
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public UserRole selectById(Integer id) {
        return null;
    }

}
