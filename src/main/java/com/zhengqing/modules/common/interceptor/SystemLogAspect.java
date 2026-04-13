package com.zhengqing.modules.common.interceptor;

import com.zhengqing.config.Constants;
import com.zhengqing.modules.common.dto.output.ApiResult;
import com.zhengqing.modules.system.entity.SysLog;
import com.zhengqing.modules.system.repository.UserRepository;
import com.zhengqing.modules.system.service.ILogService;
import com.zhengqing.utils.DateTimeUtils;
import com.zhengqing.utils.IpUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * <p> 系统日志处理 </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2019/9/18 15:25
 */
@Aspect
@Configuration
@Slf4j
@RequiredArgsConstructor
public class SystemLogAspect {

    private final UserRepository userRepository;
    private final ILogService logService;

    @Pointcut("execution(* com.zhengqing.modules.*.api.*Controller.*(..)))")
    public void systemLog() {
    }

    @Around(value = "systemLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        // Get the ip address, request path, token
        String url = request.getRequestURL().toString();
        String ip = IpUtils.getIpAdrress(request);
        String token = request.getHeader(Constants.REQUEST_HEADER);

        // Method to obtain the weaving point from the weaving point of the cut surface through the reflection mechanism
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // How to get the entry point
        Method method = signature.getMethod();
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        String methodName = "";
        if (apiOperation != null) {
            methodName = apiOperation.value();
        }

        // 记录执行时间
        long startTime = System.currentTimeMillis();
        ApiResult result = (ApiResult) joinPoint.proceed(joinPoint.getArgs());
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        log.info("**********   Url: {}, Start: {}, End: {}, Total: {}ms, Code: {}   **********", url, DateTimeUtils.dateFormat(new Date(startTime), "yyyy-MM-dd HH:mm:ss:SSS"), DateTimeUtils.dateFormat(new Date(endTime), "yyyy-MM-dd HH:mm:ss:SSS"), totalTime, result.getCode());

        // 插入系统日志表
        SysLog sysLog = new SysLog();
        sysLog.setName(methodName);
        sysLog.setUrl(url);
        sysLog.setIp(ip);
        // 获取用户信息
        if (token == null) {
            // 非法人员
            sysLog.setUserId(0);
//            sysLog.setName(result.getMessage());
        } else {
            if (userRepository.getUserInfoByToken(token) != null) {
                sysLog.setUserId(userRepository.getUserInfoByToken(token).getId());
            }
        }
        sysLog.setStatus(result.getCode());
        sysLog.setExecuteTime(totalTime + " ms");
        logService.save(sysLog);
        return result;
    }

}
