package com.hmtmcse.ss.springsecurity.modules.basic.api;

import com.hmtmcse.ss.springsecurity.modules.common.api.BaseController;
import com.hmtmcse.ss.springsecurity.modules.common.dto.output.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  <p> 首页 </p>
 *
 * @description :
 * @author : zhengqing
 * @date : 2019/8/19 13:49
 */
@RestController
@Api(description = "Home Interface")
public class IndexController extends BaseController {

    @GetMapping(value = "/login", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Log in to the system", httpMethod = "GET", response = ApiResult.class)
    public ApiResult login() {
        return ApiResult.ok("Login to the system successfully", null);
    }

}
