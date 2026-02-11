package com.zhengqing.modules.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 *  <p> Duplicate verification of database content corresponding to custom fields Annotation </p>
 *
 * @description :
 * @author : zhengqing
 * @date : 2019/9/10 9:32
 */
// Meta annotations: provide explanations for other common tags 【@Retention、@Documented、@Target、@Inherited、@Repeatable】
@Documented
/**
 * Specify life cycle:
 *      RetentionPolicy.SOURCE: Annotations are only retained in the source code stage and will be discarded and ignored by the compiler when compiling.
 *      RetentionPolicy.CLASS: The annotation is only retained until compilation is in progress, it is not loaded into the JVM.
 *      RetentionPolicy.RUNTIME Annotations can be retained until the program is running, and they will be loaded into the JVM, so they can be obtained while the program is running.
 */
@Retention(RetentionPolicy.RUNTIME)
/**
 * Specify where the annotation should be used:
 *      ElementType.ANNOTATION_TYPE You can annotate an annotation
 *      ElementType.CONSTRUCTOR You can annotate the constructor method
 *      ElementType.FIELD Properties can be annotated
 *      ElementType.LOCAL_VARIABLE You can annotate local variables
 *      ElementType.METHOD You can annotate methods
 *      ElementType.PACKAGE You can annotate a package
 *      ElementType.PARAMETER You can annotate method parameters
 *      ElementType.TYPE You can annotate a type, such as a class, interface, or enumeration
 */
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.TYPE})
@Constraint(validatedBy = FieldRepeatValidatorClass.class)
//@Repeatable(LinkVals.class)(The same field or class can be annotated repeatedly, supported after Java 1.8）
public @interface FieldRepeatValidator {

    /**
     * Entity class id field - defaults to id (this value is optional)
     * @return
     */
    String id() default "id";;

    /**
     * Annotation attribute - corresponding check field
     * @return
     */
    String field();

    /**
     * Default error message
     * @return
     */
    String message() default "Field content is duplicated!";

    Class<?>[] groups() default {};
    Class<? extends Payload>[]  payload() default {};

}

