package com.zhengqing.modules.system.api;

import com.zhengqing.modules.common.api.BaseController;
import com.zhengqing.modules.common.dto.output.ApiResult;
import com.zhengqing.modules.system.dto.input.RoleMenuQueryPara;
import com.zhengqing.modules.system.entity.RoleMenu;
import com.zhengqing.modules.system.service.IRoleMenuService;
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
 * <p> System Administration - Role-Menu Association Table interface </p>
 *
 * @author: zhengqing
 * @description:
 * @date: 2019-08-20
 *
 */
@RestController
@RequestMapping("/api/system/roleMenu")
@Api(description = "System Administration - Role-Menu Association Table interface")
public class SysRoleMenuController extends BaseController {

    @Autowired
    IRoleMenuService roleMenuService;

    @PostMapping(value = "/listPage", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Get the System Administration - Roles - Menu Associations Table list pagination", httpMethod = "POST", response = ApiResult.class)
    public ApiResult listPage(@RequestBody RoleMenuQueryPara filter) {

        Pageable pageable = PageRequest.of(filter.getPage(), filter.getLimit());
        Page<RoleMenu> page = roleMenuService.listPage(pageable, filter);
        return ApiResult.ok("Get System Administration - Roles - Menu Associations Table list pagination successfully", page);
    }

    @PostMapping(value = "/list", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "获取系统管理 - 角色-菜单关联表 列表", httpMethod = "POST", response = ApiResult.class)
    public ApiResult list(@RequestBody RoleMenuQueryPara filter) {
        List<RoleMenu> result = roleMenuService.list(filter);
        return ApiResult.ok("获取系统管理 - 角色-菜单关联表 列表成功",result);
    }

    @PostMapping(value = "/saveOrUpdate", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "保存或更新系统管理 - 角色-菜单关联表 ", httpMethod = "POST", response = ApiResult.class)
    public ApiResult saveOrUpdate(@RequestBody RoleMenu input) {
        Integer id = roleMenuService.save(input);
        return ApiResult.ok("保存系统管理 - 角色-菜单关联表 成功", id);
    }

    @PostMapping(value = "/delete", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "删除系统管理 - 角色-菜单关联表 ", httpMethod = "POST", response = ApiResult.class)
    public ApiResult delete(@RequestBody RoleMenuQueryPara input) {
        roleMenuService.deleteById(input.getId());
        return ApiResult.ok("删除系统管理 - 角色-菜单关联表 成功");
    }

    @PostMapping(value = "/detail", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "根据ID获取系统管理 - 角色-菜单关联表 信息", httpMethod = "POST", response = ApiResult.class)
    public ApiResult detail(@RequestBody RoleMenuQueryPara input) {
        RoleMenu entity = roleMenuService.selectById(input.getId());
        return ApiResult.ok("根据ID获取系统管理 - 角色-菜单关联表 信息成功", entity);
    }

    @PostMapping(value = "/saveRoleMenu", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "保存角色相关联菜单", httpMethod = "POST", response = ApiResult.class)
    public ApiResult saveRoleMenu(@RequestBody RoleMenuQueryPara input) {
        roleMenuService.saveRoleMenu( input );
        return ApiResult.ok("保存角色相关联菜单成功");
    }

}
