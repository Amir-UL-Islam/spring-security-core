package com.zhengqing.modules.system.repository;

import com.zhengqing.modules.common.entity.BaseEntity;
import com.zhengqing.modules.system.dto.input.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface DefaultRepository<T extends BaseEntity> extends JpaSpecificationExecutor<T> {
    /**
     * Build dynamic conditions for the user queries.
     */
    static <T extends BaseEntity> Specification<T> buildSpecification(UserQueryPara filter) {
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

    static <T extends BaseEntity> Specification<T> buildSpecification(LogQueryPara filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter != null) {
                if (filter.getId() != null) {
                    predicates.add(cb.equal(root.get("id"), filter.getId()));
                }
                if (StringUtils.isNotBlank(filter.getUsername())) {
                    predicates.add(cb.like(root.get("username"), "%" + filter.getUsername() + "%"));
                }
                if (filter.getStartTime() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("createTime"), filter.getStartTime()));
                }

                if (filter.getEndTime() != null) {
                    predicates.add(cb.lessThanOrEqualTo(root.get("createTime"), filter.getEndTime()));
                }

                if (filter.getUrl() != null && StringUtils.isNotBlank(filter.getUrl())) {
                    predicates.add(cb.like(root.get("url"), "%" + filter.getUrl() + "%"));
                }
            }
            query.orderBy(cb.desc(root.get("id")));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    static <T extends BaseEntity> Specification<T> buildSpecification(MenuQueryPara filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter != null) {
                if (filter.getId() != null) {
                    predicates.add(cb.equal(root.get("id"), filter.getId()));
                }
            }
            query.orderBy(cb.desc(root.get("id")));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    static <T extends BaseEntity> Specification<T> buildSpecification(RoleQueryPara filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter != null) {
                if (filter.getId() != null) {
                    predicates.add(cb.equal(root.get("id"), filter.getId()));
                }
                if (filter.getName() != null && StringUtils.isNotBlank(filter.getName())) {
                    predicates.add(cb.like(root.get("name"), "%" + filter.getName() + "%"));
                }
            }
            query.orderBy(cb.desc(root.get("id")));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    static <T extends BaseEntity> Specification<T> buildSpecification(UserRoleQueryPara filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter != null) {
                if (filter.getId() != null) {
                    predicates.add(cb.equal(root.get("id"), filter.getId()));
                }

                if (filter.getRoleId() != null) {
                    predicates.add(cb.equal(root.get("role_id"), filter.getRoleId()));
                }

                if (filter.getUserIds() != null && !filter.getUserIds().isEmpty()) {
                    predicates.add(root.get("user_id").in(filter.getUserIds()));
                }
            }
            query.orderBy(cb.desc(root.get("id")));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    static <T extends BaseEntity> Specification<T> buildSpecification(RoleMenuQueryPara filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter != null) {
                if (filter.getId() != null) {
                    predicates.add(cb.equal(root.get("id"), filter.getId()));
                }
                if (filter.getRoleId() != null) {
                    predicates.add(cb.equal(root.get("role_id"), filter.getRoleId()));
                }

                if (filter.getMenuIds() != null && !filter.getMenuIds().isEmpty()) {
                    predicates.add(root.get("menu_id").in(filter.getMenuIds()));
                }
            }
            query.orderBy(cb.desc(root.get("id")));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
