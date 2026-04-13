package com.zhengqing.modules.common.exception;

import com.zhengqing.modules.common.dto.output.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 *  <p> Global exception handler </p>
 *
 * @description: 在spring 3.2中，新增了@ControllerAdvice 注解，可以用于定义@ExceptionHandler、@InitBinder、@ModelAttribute，并应用到所有@RequestMapping中
 * @author: zhengqing
 * @date: 2019/8/25 0025 18:56
 */
@Slf4j
@RestControllerAdvice
public class MyGlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(MyGlobalExceptionHandler.class);

    /**
     * Custom exception handling
     */
    @ExceptionHandler(value = MyException.class)
    public ApiResult myException(MyException be) {
        log.error("Custom exception：", be);
        if(be.getCode() != null){
            return ApiResult.fail(be.getCode(), be.getMessage());
        }
        return ApiResult.fail( be.getMessage() );
    }

    // 参数校验异常处理 ===========================================================================
    // MethodArgumentNotValidException是springBoot中进行绑定参数校验时的异常,需要在springBoot中处理,其他需要处理ConstraintViolationException异常进行处理.

    /**
     * 方法参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult handleMethodArgumentNotValidException( MethodArgumentNotValidException e ) {
        log.error( "Method parameter verification:" + e.getMessage(), e );
        return ApiResult.fail( e.getBindingResult().getFieldError().getDefaultMessage() );
    }

    /**
     * ValidationException
     */
    @ExceptionHandler(ValidationException.class)
    public ApiResult handleValidationException(ValidationException e) {
        log.error( "ValidationException:", e );
        return ApiResult.fail( e.getCause().getMessage() );
    }

    /**
     * ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResult handleConstraintViolationException(ConstraintViolationException e) {
        log.error( "ValidationException:" + e.getMessage(), e );
        return ApiResult.fail( e.getMessage() );
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResult handlerNoFoundException(Exception e) {
        return ApiResult.fail( 404, "The path does not exist, please check whether the path is correct" );
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ApiResult handleDuplicateKeyException(DuplicateKeyException e) {
        return ApiResult.fail( "The data is duplicated, please check before submitting." );
    }




    //    ===============================================

    @ExceptionHandler(RuntimeException.class)
    public ApiResult handleRuntimeException(RuntimeException e) {
        LOG.error("System exception:", e);
        return ApiResult.fail("System exception, operation failed");
    }

    /**
     * Null pointer exception
     */
    @ExceptionHandler(NullPointerException.class)
    public ApiResult nullPointerExceptionHandler(NullPointerException ex) {
        log.error("Null pointer exception:", ex);
        return ApiResult.fail("Null pointer exception!");
    }

    /**
     * Type conversion exception
     */
    @ExceptionHandler(ClassCastException.class)
    public ApiResult classCastExceptionHandler(ClassCastException ex) {
        log.error("Type conversion exception:", ex);
        return ApiResult.fail("Type conversion exception!");
    }

    /**
     * Array out of bounds exception
     */
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ApiResult ArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException ex) {
        log.error("Array out of bounds exception:", ex);
        return ApiResult.fail("Array out of bounds exception!");
    }

    /**
     * Other errors
     */
    @ExceptionHandler({Exception.class})
    public ApiResult exception(Exception ex) {
        log.error("Other errors:", ex);
        return ApiResult.fail( 500, "Other errors："+ ex );
    }

}
