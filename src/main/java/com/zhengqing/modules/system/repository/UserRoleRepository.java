package com.zhengqing.modules.system.repository;

import com.zhengqing.modules.system.dto.input.UserRoleQueryPara;
import com.zhengqing.modules.system.entity.Role;
import com.zhengqing.modules.system.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>, DefaultRepository<UserRole> {
    /**
     * List paging
     *
     * @param page
     * @param filter
     * @return
     */
    default Page<UserRole> selectUserRoles(Pageable page, UserRoleQueryPara filter) {
        return findAll(DefaultRepository.buildSpecification(filter), page);
    }

    /**
     * List
     *
     * @param filter
     * @return
     */
    default List<UserRole> selectUserRoles(@Param("filter") UserRoleQueryPara filter) {
        return findAll(DefaultRepository.buildSpecification(filter));
    }

    /**
     * Delete data associated with users and roles based on role ID
     *
     * @param roleId:
     * @return: void
     */
    @Modifying
    @Query("DELETE FROM UserRole ur WHERE ur.roleId = :roleId")
    void deleteByRoleId(@Param("roleId") Integer roleId);

    /**
     * Query associated roles based on user ID
     *
     * @param userId:
     * @return: java.util.List<com.zhengqing.modules.system.entity.Role>
     */
    @Query("SELECT r FROM Role r JOIN UserRole ur ON r.id = ur.roleId WHERE ur.userId = :userId")
    List<Role> selectRoleByUserId(@Param("userId") Integer userId);

    @Query("SELECT r FROM Role r JOIN UserRole ur ON r.id = ur.roleId WHERE ur.userId = :userId")
    List<UserRole> findByUserId(Integer userId);
}
