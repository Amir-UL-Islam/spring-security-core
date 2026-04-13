package com.zhengqing.modules.system.api;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zhengqing.modules.common.api.BaseController;
import com.zhengqing.modules.common.dto.output.ApiResult;
import com.zhengqing.modules.system.dto.input.MenuQueryPara;
import com.zhengqing.modules.system.dto.output.MenuTreeNode;
import com.zhengqing.modules.system.entity.Menu;
import com.zhengqing.modules.system.service.IMenuService;
import com.zhengqing.utils.TreeBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p> System Management-Menu Table Interface </p>
 *
 * @author: zhengqing
 * @description:
 * @date: 2019-08-19
 *
 */
@RestController
@RequestMapping("/api/system/menu")
@Api(description = "System Management - Menu Table Interface")
@RequiredArgsConstructor
public class SysMenuController extends BaseController {

    private final IMenuService menuService;

    @PostMapping(value = "/treeMenu", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Get menu tree", httpMethod = "POST", response = ApiResult.class)
    public ApiResult treeMenu() {
        List<Menu> list = menuService.listTreeMenu();
        List<MenuTreeNode> menuTreeNodeList = Lists.newArrayList();
        if (list != null && !list.isEmpty()) {
            list.forEach(temp -> {
                MenuTreeNode menuTreeNode = new MenuTreeNode();
                BeanUtil.copyProperties(temp, menuTreeNode);
                menuTreeNodeList.add(menuTreeNode);
            });
        }
        List<MenuTreeNode> menuTreeNodeList2 = TreeBuilder.buildMenuTree(menuTreeNodeList);

        menuTreeNodeList2.stream().sorted(Comparator.comparing(MenuTreeNode::getSortNo)).collect(Collectors.toList());
        JSONObject json = new JSONObject();
        json.put("menuList", list);
        json.put("menuTree", menuTreeNodeList2);
        return ApiResult.ok("Get menu tree successfully", json);
    }

    @PostMapping(value = "/save", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Save Menu ", httpMethod = "POST", response = ApiResult.class)
    public ApiResult save(@RequestBody @Validated Menu input) {
        Integer id = menuService.save(input);
        return ApiResult.ok("Save menu successfully", id);
    }

    @PostMapping(value = "/delete", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Delete Menu", httpMethod = "POST", response = ApiResult.class)
    public ApiResult delete(@RequestBody MenuQueryPara input) {
        // If there is a submenu under this menu, you are prompted to delete the submenu first.
        List<Menu> menuList = menuService.findByParentId(input.getId());
        if (!CollectionUtils.isEmpty(menuList)) {
//            menuList.forEach(e -> menuService.deleteById(e.getId()));
            return ApiResult.fail("There is a submenu under this menu, please delete the submenu first!");
        }
        menuService.deleteById(input.getId());
        return ApiResult.ok("Delete menu successfully");
    }

    // Not used below for now ================================================

    @PostMapping(value = "/listPage", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Get system management-menu table list paging", httpMethod = "POST", response = ApiResult.class)
    public ApiResult listPage(@RequestBody MenuQueryPara filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getLimit());
        menuService.listPage(pageable, filter);
        return ApiResult.ok("Obtain system management-menu table list paging successfully", pageable);
    }

    @PostMapping(value = "/list", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Get system management-menu table list", httpMethod = "POST", response = ApiResult.class)
    public ApiResult list(@RequestBody MenuQueryPara filter) {
        List<Menu> result = menuService.list(filter);
        return ApiResult.ok("Obtain system management-menu table list successfully", result);
    }

    @PostMapping(value = "/getById", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "Get system management-menu table information", httpMethod = "POST", response = ApiResult.class)
    public ApiResult getById(@RequestBody MenuQueryPara input) {
        Menu entity = menuService.selectById(input.getId());
        return ApiResult.ok("Obtained system management-menu table information successfully", entity);
    }

}
