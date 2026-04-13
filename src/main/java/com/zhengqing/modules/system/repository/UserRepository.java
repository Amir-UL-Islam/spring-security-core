package com.zhengqing.modules.system.repository;

import com.zhengqing.modules.common.validator.Update;
import com.zhengqing.modules.system.dto.input.UserQueryPara;
import com.zhengqing.modules.system.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>, DefaultRepository<User> {

    /**
     * JPQL-backed paging query with optional filters.
     */
    default Page<User> selectUsers(Pageable page, UserQueryPara filter) {
        return findAll(DefaultRepository.buildSpecification(filter), page);
    }


    /**
     * JPQL-backed list query with optional filters.
     */
    default List<User> selectUsers(UserQueryPara filter) {
        return findAll(DefaultRepository.buildSpecification(filter));
    }

    @Query("select u from User u where u.token = ?1")
    User getUserInfoByToken(String token);

    @Query("select u from User u where u.id = ?1")
    User selectById(@NotNull(message = "User id cannot be empty", groups = {Update.class}) Integer id);

    @Query("select u from User u where u.username = ?1")
    User selectUserByUsername(String username);

    @Query("select u from User u where u.qqOppenId = ?1")
    User getUserInfoByQQ(String qqOppenId);

    @Query(value = "SELECT su.* " +
            " FROM t_sys_user su " +
            " LEFT JOIN t_sys_user_role sur ON su.id=sur.user_id " +
            " WHERE sur.role_id = :roleId", nativeQuery = true)
    List<User> selectUserByRoleId(@Param("roleId") Integer roleId);


    @Query("SELECT u FROM User u WHERE u.username = :username")
    List<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.token = :token")
    List<User> findByToken(String token);

}
