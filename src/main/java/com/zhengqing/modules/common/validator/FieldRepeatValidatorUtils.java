package com.zhengqing.modules.common.validator;

import com.zhengqing.modules.common.exception.MyException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> Database field content duplication judgment processing tool class </p>
 *
 * @author：  zhengqing <br/>
 * @date：  2019/9/10$ 9:28$ <br/>
 * @version：  <br/>
 */
@Component
public class FieldRepeatValidatorUtils {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * If you need to check whether the same field is duplicated in the later stage of checking data
     * TODO, set 'field' to , or - split... ;
     * TODO If the id is not the only consideration to pass the value to judge or take the field value of the second field,
     * TODO take the id     *
     *
     * @param field：Validate fields
     * @param object：Object data
     * @param message：Callback to the frontend prompt message
     * @return: boolean
     */
    public boolean fieldRepeat(String id, String field, Object object, String message) {
        if (object == null) {
            return true;
        }

        FieldValueData values = getFieldValue(id, field, object);
        if (values.fieldValue == null) {
            return true;
        }

        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            Root<?> root = query.from(object.getClass());

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.equal(root.get(field), values.fieldValue));
            if (values.idValue != null) {
                predicates.add(builder.notEqual(root.get(id), values.idValue));
            }

            query.select(builder.count(root)).where(predicates.toArray(new Predicate[0]));
            Long count = entityManager.createQuery(query).getSingleResult();
            if (count != null && count > 0) {
                throw new MyException(message);
            }
            return true;
        } catch (IllegalArgumentException e) {
            throw new MyException("FieldRepeatValidator config error: " + e.getMessage());
        }
    }

    /**
     * 获取id、校验字段值
     */
    private FieldValueData getFieldValue(String id, String field, Object object) {
        Object idValue = readFieldValue(object, id);
        Object fieldValue = readFieldValue(object, field);
        return new FieldValueData(idValue, fieldValue);
    }

    private Object readFieldValue(Object object, String fieldName) {
        Class<?> type = object.getClass();
        while (type != null) {
            try {
                Field target = type.getDeclaredField(fieldName);
                target.setAccessible(true);
                return target.get(object);
            } catch (NoSuchFieldException e) {
                type = type.getSuperclass();
            } catch (IllegalAccessException e) {
                throw new MyException("Cannot read field: " + fieldName);
            }
        }
        throw new MyException("Cannot find field: " + fieldName);
    }

    private static class FieldValueData {
        private final Object idValue;
        private final Object fieldValue;

        private FieldValueData(Object idValue, Object fieldValue) {
            this.idValue = idValue;
            this.fieldValue = fieldValue;
        }
    }

}
