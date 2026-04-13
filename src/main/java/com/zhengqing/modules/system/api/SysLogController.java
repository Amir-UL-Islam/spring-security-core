package com.zhengqing.modules.system.api;

import com.zhengqing.modules.common.api.BaseController;
import com.zhengqing.modules.common.dto.output.ApiResult;
import com.zhengqing.modules.system.dto.input.LogQueryPara;
import com.zhengqing.modules.system.service.ILogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p> System Management - Log Table Interface </p>
 *
 * @author: zhengqing
 * @description:
 * @date: 2019-09-18 10:51:57
 *
 */
@RestController
@RequestMapping("/api/system/log")
@Api(description = "System Management - Log Table Interface")
@RequiredArgsConstructor
public class SysLogController extends BaseController {

    private final ILogService logService;

    @PostMapping(value = "/listPage", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Get system management - log table list paging", httpMethod = "POST", response = ApiResult.class)
    public ApiResult listPage(@RequestBody LogQueryPara filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getLimit());
        logService.listPage(pageable, filter);
        return ApiResult.ok("Get system management - log table list pagination successful", pageable);
    }

//    @PostMapping(value = "/list", produces = "application/json;charset=utf-8")
//    @ApiOperation(value = "获取系统管理 - 日志表列表", httpMethod = "POST", response = ApiResult.class)
//    public ApiResult list(@RequestBody LogInput filter) {
//        List<Log> result = logService.list(filter);
//        return ApiResult.ok("获取系统管理 - 日志表列表成功",result);
//    }
//
//    @PostMapping(value = "/saveOrUpdate", produces = "application/json;charset=utf-8")
//    @ApiOperation(value = "保存或更新系统管理 - 日志表", httpMethod = "POST", response = ApiResult.class)
//    public ApiResult saveOrUpdate(@RequestBody Log input) {
//        Integer id = logService.save(input);
//        return ApiResult.ok("保存系统管理 - 日志表成功", id);
//    }
//
//    @PostMapping(value = "/delete", produces = "application/json;charset=utf-8")
//    @ApiOperation(value = "删除系统管理 - 日志表", httpMethod = "POST", response = ApiResult.class)
//    public ApiResult delete(@RequestBody LogInput input) {
//        logService.deleteById(input.getId());
//        return ApiResult.ok("删除系统管理 - 日志表成功");
//    }
//
//    @PostMapping(value = "/detail", produces = "application/json;charset=utf-8")
//    @ApiOperation(value = "根据ID获取系统管理 - 日志表信息", httpMethod = "POST", response = ApiResult.class)
//    public ApiResult detail(@RequestBody LogInput input) {
//        Log entity = logService.selectById(input.getId());
//        return ApiResult.ok("根据ID获取系统管理 - 日志表信息成功", entity);
//    }

}
