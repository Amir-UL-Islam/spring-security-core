package com.zhengqing.config.security.login;

import com.zhengqing.modules.common.dto.output.ApiResult;
import com.zhengqing.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p> Authentication authority entry - accessing all interfaces without logging in will intercept this </p>
 *
 * @author : zhengqing
 * @description : Return JSON format data when the front and back ends are separated
 * @date : 2019/10/11 17:32
 */
@Slf4j
@Component
public class AdminAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        // Not logged in or token expired
        if (e!=null){
            ResponseUtils.out(response, ApiResult.expired(e.getMessage()));
        } else {
            ResponseUtils.out(response, ApiResult.expired("jwtToken expires!"));
        }
    }

}
