package com.zhengqing.config.security.service.impl;

import com.zhengqing.config.security.dto.SecurityUser;
import com.zhengqing.modules.system.entity.Role;
import com.zhengqing.modules.system.entity.User;
import com.zhengqing.modules.system.entity.UserRole;
import com.zhengqing.modules.system.repository.RoleRepository;
import com.zhengqing.modules.system.repository.UserRepository;
import com.zhengqing.modules.system.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * <p> 自定义userDetailsService - 认证用户详情 </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2019/10/14 17:46
 */
@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    /***
     * Get user information based on account
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Get user information from a database
        List<User> userList = userRepository.findByUsername(username);
        User user;
        // Determine whether the user exists
        if (!CollectionUtils.isEmpty(userList)) {
            user = userList.get(0);
        } else {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        // Return UserDetails implementation class
        return new SecurityUser(user, getUserRoles(user.getId()));
    }

    /***
     * Obtain user permissions and basic information based on token
     *
     * @param token:
     * @return: com.zhengqing.config.security.dto.SecurityUser
     */
    public SecurityUser getUserByToken(String token) {
        User user = null;
        List<User> loginList = userRepository.findByToken(token);
        if (!CollectionUtils.isEmpty(loginList)) {
            user = loginList.get(0);
        }
        return user != null ? new SecurityUser(user, getUserRoles(user.getId())) : null;
    }

    /**
     * Get role permission information based on user ID
     *
     * @param userId
     * @return
     */
    private List<Role> getUserRoles(Integer userId) {
        List<UserRole> userRoles = userRoleRepository.findByUserId(userId);
        List<Role> roleList = new LinkedList<>();
        for (UserRole userRole : userRoles) {
            Role role = roleRepository.selectById(userRole.getRoleId());
            roleList.add(role);
        }
        return roleList;
    }

}
