package com.zhengqing.modules.system.repository;

import com.zhengqing.modules.system.dto.input.RoleQueryPara;
import com.zhengqing.modules.system.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long>, DefaultRepository<Role> {


    /**
     * List paging
     *
     * @param page
     * @param filter
     * @return
     */
    default Page<Role> selectRoles(Pageable page, RoleQueryPara filter) {
        return findAll(DefaultRepository.buildSpecification(filter), page);
    }

    /**
     * List
     *
     * @param filter
     * @return
     */
    default List<Role> selectRoles(@Param("filter") RoleQueryPara filter) {
        return findAll(DefaultRepository.buildSpecification(filter));
    }

    /**
     * Query role collection by user ID
     *
     * @param userId:
     * @return: java.util.List<Role>
     */
    @Query("SELECT r FROM Role r JOIN UserRole ur ON r.id = ur.roleId WHERE ur.userId = :userId")
    List<Role> selectRoleByUserId(@Param("userId") Integer userId);

    /**
     * Query role collection by menu ID
     *
     * @param menuId:
     * @return: java.util.List<Role>
     */
    @Query("SELECT r FROM Role r JOIN RoleMenu rm ON r.id = rm.roleId WHERE rm.menuId = :menuId")
    List<Role> selectRoleByMenuId(@Param("menuId") Integer menuId);

    @Query("SELECT r FROM Role r WHERE r.id = :roleId")
    Role selectById(@Param("roleId") Integer roleId);
}
