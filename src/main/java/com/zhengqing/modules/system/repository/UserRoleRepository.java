package com.zhengqing.modules.system.repository;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhengqing.modules.system.dto.input.UserRoleQueryPara;
import com.zhengqing.modules.system.entity.Role;
import com.zhengqing.modules.system.entity.UserRole;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    /**
     * List paging
     *
     * @param page
     * @param filter
     * @return
     */
    List<UserRole> selectUserRoles(Pageable page, @Param("filter") UserRoleQueryPara filter);

    /**
     * List
     *
     * @param filter
     * @return
     */
    List<UserRole> selectUserRoles(@Param("filter") UserRoleQueryPara filter);

    /**
     * Delete data associated with users and roles based on role ID
     *
     * @param roleId:
     * @return: void
     */
    void deleteByRoleId(@Param("roleId") Integer roleId);

    /**
     * Query associated roles based on user ID
     *
     * @param userId:
     * @return: java.util.List<com.zhengqing.modules.system.entity.Role>
     */
    List<Role> selectRoleByUserId(@Param("userId") Integer userId);

    List<UserRole> selectList(Wrapper<UserRole> userId);
}
