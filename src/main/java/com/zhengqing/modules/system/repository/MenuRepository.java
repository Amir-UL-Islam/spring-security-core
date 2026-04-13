package com.zhengqing.modules.system.repository;

import com.zhengqing.modules.system.dto.input.MenuQueryPara;
import com.zhengqing.modules.system.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {


    /**
     * 列表分页
     *
     * @param page
     * @param filter
     * @return
     */
    default Page<Menu> selectMenus(Pageable page, @Param("filter") MenuQueryPara filter) {
        return findAll(DefaultRepository.buildSpecification(filter), page);
    }

    /**
     * 列表
     *
     * @param filter
     * @return
     */
    default List<Menu> selectMenus(@Param("filter") MenuQueryPara filter) {
        return findAll(DefaultRepository.buildSpecification(filter));
    }

    /**
     * Get information through menu coding
     *
     * @param resources:
     * @return: com.zhengqing.modules.system.entity.Menu
     */
    @Query("SELECT m FROM Menu m WHERE m.resources = :resources")
    Menu findByResources(@Param("resources") String resources);

    /**
     * 根据角色查询用户权限
     *
     * @param roleId:
     * @return: java.util.List<com.zhengqing.modules.system.entity.Menu>
     */
    @Query("SELECT m FROM Menu m JOIN RoleMenu rm ON m.id = rm.menuId WHERE rm.roleId = :roleId")
    List<Menu> selectMenuByRoleId(@Param("roleId") Integer roleId);

    @Query(value = "select * from t_sys_menu", nativeQuery = true)
    List<Menu> selectList();

    @Query("SELECT m FROM Menu m WHERE m.parentId = :parentId")
    List<Menu> findByParentId(@Param("parentId") String parentId);
}
