package com.zhengqing.modules.system.repository;

import com.zhengqing.modules.system.dto.input.RoleMenuQueryPara;
import com.zhengqing.modules.system.entity.Menu;
import com.zhengqing.modules.system.entity.RoleMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleMenuRepository extends JpaRepository<RoleMenu, Long>, DefaultRepository<RoleMenu> {


    /**
     * List paging
     *
     * @param page
     * @param filter
     * @return
     */
    default Page<RoleMenu> selectRoleMenus(Pageable page, RoleMenuQueryPara filter) {
        return findAll(DefaultRepository.buildSpecification(filter), page);
    }

    /**
     * List
     *
     * @param filter
     * @return
     */
    default List<RoleMenu> selectRoleMenus(@Param("filter") RoleMenuQueryPara filter) {
        return findAll(DefaultRepository.buildSpecification(filter));
    }

    /**
     * Delete user and menu related data based on role ID
     *
     * @param roleId:
     * @return: void
     */
    @Modifying
    @Query("DELETE FROM RoleMenu rm WHERE rm.roleId = :roleId")
    void deleteByRoleId(@Param("roleId") Integer roleId);


    /**
     * Query context menu based on role ID
     *
     * @param roleId:
     * @return: java.util.List<com.zhengqing.modules.system.entity.Menu>
     */
    @Query("SELECT m FROM Menu m JOIN RoleMenu rm ON m.id = rm.menuId WHERE rm.roleId = :roleId")
    List<Menu> selectMenusByRoleId(@Param("roleId") Integer roleId);

    @Query("SELECT rm FROM RoleMenu rm WHERE rm.menuId = :menuId")
    List<RoleMenu> findByMenuId(Integer menuId);
}
