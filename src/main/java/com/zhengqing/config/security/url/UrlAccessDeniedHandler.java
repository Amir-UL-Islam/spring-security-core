package com.zhengqing.config.security.url;

import com.zhengqing.modules.common.dto.output.ApiResult;
import com.zhengqing.modules.common.enumeration.ResultCode;
import com.zhengqing.utils.ResponseUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * <p> Authentication URL permissions - No permission to access the interface after logging in - Customize the 403 No permission response content </p>
 *
 * @author : zhengqing
 * @description : Permission processing after login [Note: It must be distinguished from permission processing when not logged in~]
 * @date : 2019/10/14 18:52
 */
@Component
public class UrlAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtils.out(response, ApiResult.fail(ResultCode.UNAUTHORIZED.getCode(), e.getMessage()));
    }
}
