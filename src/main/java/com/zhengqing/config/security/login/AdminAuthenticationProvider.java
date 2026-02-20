package com.zhengqing.config.security.login;

import com.zhengqing.config.Constants;
import com.zhengqing.config.security.service.impl.UserDetailsServiceImpl;
import com.zhengqing.config.security.dto.SecurityUser;
import com.zhengqing.modules.system.repository.UserRepository;
import com.zhengqing.utils.PasswordUtils;
import com.zhengqing.modules.system.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * <p> Custom authentication processing </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2019/10/12 14:49
 */
@Component
@RequiredArgsConstructor
public class AdminAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Get the username and password returned after input in the front-end form
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        SecurityUser userInfo = (SecurityUser) userDetailsService.loadUserByUsername(userName);

        boolean isValid = PasswordUtils.isValidPassword(password, userInfo.getPassword(), userInfo.getCurrentUserInfo().getSalt());
        // Verify password
        if (!isValid) {
            throw new BadCredentialsException("Wrong password！");
        }

        // Processing logic when front-end and back-end are separated...
        // Update login token
//        String token = PasswordUtils.encodePassword(String.valueOf(System.currentTimeMillis()), userInfo.getCurrentUserInfo().getSalt());
        // Role code owned by the current user
        String roleCodes = userInfo.getRoleCodes();
        // Generate jwt access token
        String jwt = Jwts.builder()
                // USER ROLE
                .claim(Constants.ROLE_LOGIN, roleCodes)
                // Topic - save username
                .setSubject(authentication.getName())
                // Expiration time - 30 minutes
//                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                // Encryption algorithms and keys
                .signWith(SignatureAlgorithm.HS512, Constants.SALT)
                .compact();


        User user = userRepository.selectById(userInfo.getCurrentUserInfo().getId());
        user.setToken(jwt);
//        userMapper.updateById(user);
        userRepository.save(user);
        userInfo.getCurrentUserInfo().setToken(jwt);
        return new UsernamePasswordAuthenticationToken(userInfo, password, userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
