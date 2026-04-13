package com.zhengqing.modules.system.api;

import com.zhengqing.modules.common.api.BaseController;
import com.zhengqing.modules.common.dto.output.ApiResult;
import com.zhengqing.modules.system.dto.input.RoleQueryPara;
import com.zhengqing.modules.system.entity.Role;
import com.zhengqing.modules.system.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p> System Administration - Role Table interface </p>
 *
 * @author: zhengqing
 * @description:
 * @date: 2019-08-20
 *
 */
@RestController
@RequestMapping("/api/system/role")
@Api(description = "System Administration - Role Table interface")
public class SysRoleController extends BaseController {

    @Autowired
    IRoleService roleService;

    @PostMapping(value = "/listPage", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Get the System Administration - Roles Table list pagination", httpMethod = "POST", response = ApiResult.class)
    public ApiResult listPage(@RequestBody RoleQueryPara filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getLimit());
        roleService.listPage(pageable, filter);
        return ApiResult.ok("Get System Administration - Role Table The list pagination is successful", pageable);
    }

    @PostMapping(value = "/list", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "获取系统管理-角色表 列表", httpMethod = "POST", response = ApiResult.class)
    public ApiResult list(@RequestBody RoleQueryPara filter) {
        List<Role> result = roleService.list(filter);
        return ApiResult.ok("获取系统管理-角色表 列表成功",result);
    }

    @PostMapping(value = "/saveOrUpdate", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "保存或更新角色", httpMethod = "POST", response = ApiResult.class)
    public ApiResult saveOrUpdate(@RequestBody @Validated Role input) {
        Integer id = roleService.save(input);
        return ApiResult.ok("保存角色成功", id);
    }

    @PostMapping(value = "/delete", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "删除角色 ", httpMethod = "POST", response = ApiResult.class)
    public ApiResult delete(@RequestBody RoleQueryPara input) {
        roleService.deleteById(input.getId());
        return ApiResult.ok("删除角色成功");
    }

    @PostMapping(value = "/detail", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "获取角色信息", httpMethod = "POST", response = ApiResult.class)
    public ApiResult detail(@RequestBody RoleQueryPara input) {
        Role entity = roleService.selectById(input.getId());
        return ApiResult.ok("获取角色信息成功", entity);
    }

}
