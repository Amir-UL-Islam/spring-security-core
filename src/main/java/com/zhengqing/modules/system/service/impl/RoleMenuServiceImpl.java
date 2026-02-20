package com.zhengqing.modules.system.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhengqing.modules.system.dto.input.RoleMenuQueryPara;
import com.zhengqing.modules.system.entity.RoleMenu;
import com.zhengqing.modules.system.repository.RoleMenuRepository;
import com.zhengqing.modules.system.service.IRoleMenuService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p> System Management - Role-Menu Association Table Service Implementation Class </p>
 *
 * @author: zhengqing
 * @date: 2019-08-20
 */
@Service
@Transactional
@RequiredArgsConstructor
public class RoleMenuServiceImpl implements IRoleMenuService {

    private final RoleMenuRepository roleMenuRepository;

    @Override
    public void listPage(Page<RoleMenu> page, RoleMenuQueryPara filter) {
        int pageIndex = Math.max(page.getCurrent() - 1, 0);
        int pageSize = page.getSize();
        page.setRecords(roleMenuRepository.selectRoleMenus(PageRequest.of(pageIndex, pageSize), filter));
    }

    @Override
    public List<RoleMenu> list(RoleMenuQueryPara filter) {
        return roleMenuRepository.selectRoleMenus(filter);
    }

    @Override
    public void saveRoleMenu(RoleMenuQueryPara para) {
        Integer roleId = para.getRoleId();
        String menuIds = para.getMenuIds();
//        roleMenuMapper.deleteByRoleId(roleId);
        roleMenuRepository.deleteByRoleId(roleId);
        if (StringUtils.isNotBlank(menuIds)) {
            String[] menuIdArrays = menuIds.split(",");
            if (menuIdArrays.length > 0) {
                for (String menuId : menuIdArrays) {
                    RoleMenu roleMenu = new RoleMenu();
                    roleMenu.setRoleId(roleId);
                    roleMenu.setMenuId(Integer.parseInt(menuId));
//                    roleMenuMapper.insert(roleMenu);
                    roleMenuRepository.save(roleMenu);
                }
            }
        }
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public RoleMenu selectById(Integer id) {
        return null;
    }

    @Override
    public Integer save(RoleMenu para) {
        if (para.getId() != null) {
//            roleMenuMapper.updateById(para);
            roleMenuRepository.save(para);
        } else {
//            roleMenuMapper.insert(para);
            roleMenuRepository.save(para);
        }
        return para.getId();
    }
}
