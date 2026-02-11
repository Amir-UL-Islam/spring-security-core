package com.zhengqing.modules.system.repository;

import com.zhengqing.modules.common.validator.Update;
import com.zhengqing.modules.system.dto.input.UserQueryPara;
import com.zhengqing.modules.system.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * JPQL-backed paging query with optional filters.
     */
    default List<User> selectUsers(Pageable page, UserQueryPara filter) {
        return findAll(buildSpecification(filter), page).getContent();
    }

    /**
     * JPQL-backed list query with optional filters.
     */
    default List<User> selectUsers(UserQueryPara filter) {
        return findAll(buildSpecification(filter));
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

    /**
     * Build dynamic conditions for the user queries.
     */
    static Specification<User> buildSpecification(UserQueryPara filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter != null) {
                if (filter.getId() != null) {
                    predicates.add(cb.equal(root.get("id"), filter.getId()));
                }
                if (StringUtils.isNotBlank(filter.getUsername())) {
                    predicates.add(cb.like(root.get("username"), "%" + filter.getUsername() + "%"));
                }
                if (StringUtils.isNotBlank(filter.getAccount())) {
                    predicates.add(cb.equal(root.get("username"), filter.getAccount()));
                }
            }
            query.orderBy(cb.desc(root.get("id")));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
