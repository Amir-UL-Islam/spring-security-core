package com.zhengqing.modules.system.repository;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhengqing.modules.system.dto.input.RoleMenuQueryPara;
import com.zhengqing.modules.system.entity.Menu;
import com.zhengqing.modules.system.entity.RoleMenu;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleMenuRepository extends JpaRepository<RoleMenu, Long> {


    /**
     * List paging
     *
     * @param page
     * @param filter
     * @return
     */
    List<RoleMenu> selectRoleMenus(Pageable page, @Param("filter") RoleMenuQueryPara filter);

    /**
     * List
     *
     * @param filter
     * @return
     */
    List<RoleMenu> selectRoleMenus(@Param("filter") RoleMenuQueryPara filter);

    /**
     * Delete user and menu related data based on role ID
     *
     * @param roleId:
     * @return: void
     */
    void deleteByRoleId(@Param("roleId") Integer roleId);


    /**
     * Query context menu based on role ID
     *
     * @param roleId:
     * @return: java.util.List<com.zhengqing.modules.system.entity.Menu>
     */
    List<Menu> selectMenusByRoleId(@Param("roleId") Integer roleId);

    List<RoleMenu> selectList(Wrapper<RoleMenu> menuId);
}
