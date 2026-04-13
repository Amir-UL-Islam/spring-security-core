package com.zhengqing.modules.system.api;

import com.zhengqing.modules.common.api.BaseController;
import com.zhengqing.modules.common.dto.output.ApiResult;
import com.zhengqing.modules.system.dto.input.UserRoleQueryPara;
import com.zhengqing.modules.system.entity.UserRole;
import com.zhengqing.modules.system.service.IUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p> System Administration - User Role Association Table  接口 </p>
 *
 * @author: zhengqing
 * @description:
 * @date: 2019-08-20
 *
 */
@RestController
@RequestMapping("/api/system/userRole")
@Api(description = "System Administration - User Role Association Table interface")
public class SysUserRoleController extends BaseController {

    @Autowired
    IUserRoleService userRoleService;

    @PostMapping(value = "/listPage", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Get the System Administration - User Role Association Table list pagination", httpMethod = "POST", response = ApiResult.class)
    public ApiResult listPage(@RequestBody UserRoleQueryPara filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getLimit());
        userRoleService.listPage(pageable, filter);
        return ApiResult.ok("Get System Administration - User Role Association Table list pagination successfully", pageable);
    }

    @PostMapping(value = "/list", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Get the System Administration - User Role Association Table list", httpMethod = "POST", response = ApiResult.class)
    public ApiResult list(@RequestBody UserRoleQueryPara filter) {
        List<UserRole> result = userRoleService.list(filter);
        return ApiResult.ok("Get the System Administration - User Role Association Table list successfully", result);
    }

    @PostMapping(value = "/saveOrUpdate", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Save or update System Administration - User Role Association table", httpMethod = "POST", response = ApiResult.class)
    public ApiResult saveOrUpdate(@RequestBody UserRole input) {
        Integer id = userRoleService.save(input);
        return ApiResult.ok("Save the System Administration - User Role Association Table successfully", id);
    }

    @PostMapping(value = "/delete", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Delete System Administration - User Role Association table", httpMethod = "POST", response = ApiResult.class)
    public ApiResult delete(@RequestBody UserRoleQueryPara input) {
        userRoleService.deleteById(input.getId());
        return ApiResult.ok("Delete System Administration - User Role Association Table successfully");
    }

    @PostMapping(value = "/detail", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Get the System Administration - User Role Association Table information based on the ID", httpMethod = "POST", response = ApiResult.class)
    public ApiResult detail(@RequestBody UserRoleQueryPara input) {
        UserRole entity = userRoleService.selectById(input.getId());
        return ApiResult.ok("The user role association information was successfully obtained", entity);
    }

    @PostMapping(value = "/saveUserRole", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Save the user associated with the role", httpMethod = "POST", response = ApiResult.class)
    public ApiResult saveUserRole(@RequestBody UserRoleQueryPara input) {
        userRoleService.saveUserRole(input);
        return ApiResult.ok("Save the role associated with the user success");
    }

}
