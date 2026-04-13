package com.zhengqing.modules.system.api;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.zhengqing.modules.common.api.BaseController;
import com.zhengqing.modules.common.dto.output.ApiResult;
import com.zhengqing.modules.system.dto.input.UserQueryPara;
import com.zhengqing.modules.system.dto.model.UserInfoVO;
import com.zhengqing.modules.system.dto.output.UserTreeNode;
import com.zhengqing.modules.system.entity.User;
import com.zhengqing.modules.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> System Management-User Basic Information Table Interface </p>
 *
 * @author: zhengqing
 * @description:
 * @date: 2019-08-19
 */
@RestController
@RequestMapping("/api/system/user")
@Api(description = "System Management-User Basic Information Table Interface")
public class SysUserController extends BaseController {

    @Autowired
    IUserService userService;

    @PostMapping(value = "/getCurrentUserInfo", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Get current logged in user information", httpMethod = "POST", response = ApiResult.class, notes = "获取当前登录用户信息")
    public ApiResult getCurrentUserInfo(@RequestHeader(name = "X-Token") String token) {
        UserInfoVO info = userService.getCurrentUserInfo(token);
        return ApiResult.ok(200, "Successfully obtained current logged in user information", info);
    }

    @PostMapping(value = "/listPage", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Get System Management-User Basic Information Table List Pagination", httpMethod = "POST", response = ApiResult.class)
    public ApiResult listPage(@RequestBody UserQueryPara filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getLimit());
        Page<User> results = userService.listPage(pageable, filter);
        return ApiResult.ok("Obtain system management-user basic information table list pagination successful", results);
    }

    @PostMapping(value = "/treeUser", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Get user tree", httpMethod = "POST", response = ApiResult.class)
    public ApiResult treeUser() {
        List<User> list = userService.selectList(null);
        List<UserTreeNode> userTreeNodeList = new ArrayList<>();
        list.forEach(temp -> {
            UserTreeNode userTreeNode = new UserTreeNode();
            BeanUtil.copyProperties(temp, userTreeNode);
            userTreeNodeList.add(userTreeNode);
        });
        JSONObject json = new JSONObject();
        json.put("userList", list);
        json.put("userTree", userTreeNodeList);
        return ApiResult.ok("Obtaining user tree successfully", json);
    }

    @PostMapping(value = "/list", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Get system management-user basic information table list", httpMethod = "POST", response = ApiResult.class)
    public ApiResult list(@RequestBody UserQueryPara filter) {
        List<User> result = userService.list(filter);
        return ApiResult.ok("Obtain system management-user basic information table list successfully", result);
    }

    @PostMapping(value = "/save", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Save system management-user basic information table", httpMethod = "POST", response = ApiResult.class)
    // Groups and default verification are applied at the same time
    // - if attributes without groups and attributes with groups want to be verified at the same time,
    // they must be specified in the value array at the same time.
    // To start attributes without groups, specify them through Default.class
    public ApiResult save(@RequestBody @Validated User input) {
        Integer id = userService.save(input);
        return ApiResult.ok("System management-user basic information table saved successfully", id);
    }

    @PostMapping(value = "/updatePersonalInfo", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Modify personal information", httpMethod = "POST", response = ApiResult.class)
    public ApiResult updatePersonalInfo(@RequestBody User input) {
        Integer id = userService.updatePersonalInfo(input);
        return ApiResult.ok("Personal information modified successfully", id);
    }

    @PostMapping(value = "/delete", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Delete system management-user basic information table", httpMethod = "POST", response = ApiResult.class)
    public ApiResult delete(@RequestBody UserQueryPara input) {
        userService.deleteById(input.getId());
        return ApiResult.ok("System management-user basic information table deleted successfully");
    }

    @PostMapping(value = "/getById", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Get system management-user basic information table information", httpMethod = "POST", response = ApiResult.class)
    public ApiResult getById(@RequestBody UserQueryPara input) {
        User entity = userService.selectById(input.getId());
        return ApiResult.ok("Obtained system management-user basic information table information successfully", entity);
    }

}
