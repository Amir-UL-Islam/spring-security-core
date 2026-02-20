package com.zhengqing.modules.system.repository;

import com.zhengqing.modules.system.dto.input.RoleQueryPara;
import com.zhengqing.modules.system.entity.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {


    /**
     * List paging
     *
     * @param page
     * @param filter
     * @return
     */
    List<Role> selectRoles(Pageable page, @Param("filter") RoleQueryPara filter);

    /**
     * List
     *
     * @param filter
     * @return
     */
    List<Role> selectRoles(@Param("filter") RoleQueryPara filter);

    /**
     * Query role collection by user ID
     *
     * @param userId:
     * @return: java.util.List<Role>
     */
    List<Role> selectRoleByUserId(@Param("userId") Integer userId);

    /**
     * Query role collection by menu ID
     *
     * @param menuId:
     * @return: java.util.List<Role>
     */
    List<Role> selectRoleByMenuId(@Param("menuId") Integer menuId);

    Role selectById(Integer roleId);
}
