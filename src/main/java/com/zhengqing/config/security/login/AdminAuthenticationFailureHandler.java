package com.zhengqing.config.security.login;

import com.zhengqing.modules.common.dto.output.ApiResult;
import com.zhengqing.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  <p> Authentication failure handling - return json data format when front-end and back-end are separated </p>
 *
 * @description :
 * @author : zhengqing
 * @date : 2019/10/12 15:33
 */
@Slf4j
@Component
public class AdminAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ApiResult result;
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            result = ApiResult.fail(e.getMessage());
        } else if (e instanceof LockedException) {
            result = ApiResult.fail("The account is locked, please contact the administrator!");
        } else if (e instanceof CredentialsExpiredException) {
            result = ApiResult.fail("The certificate has expired, please contact the administrator!");
        } else if (e instanceof AccountExpiredException) {
            result = ApiResult.fail("The account has expired, please contact the administrator!");
        } else if (e instanceof DisabledException) {
            result = ApiResult.fail("Account is disabled, please contact the administrator!");
        } else {
            log.error("Login failed:", e);
            result = ApiResult.fail("Login failed!");
        }
        ResponseUtils.out(response, result);
    }

}
