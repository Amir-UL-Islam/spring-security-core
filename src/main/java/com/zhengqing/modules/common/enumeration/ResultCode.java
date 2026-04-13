package com.zhengqing.modules.common.enumeration;


/**
 *  <p> Response code enumeration - refer to the semantics of HTTP status codes </p>
 *
 * @description :
 * @author : zhengqing
 * @date : 2019/8/22 11:09
 */
public enum ResultCode {
    //成功
    SUCCESS( 200, "SUCCESS" ),
    //失败
    FAILURE( 400, "FAILURE" ),
    // 未登录
    UN_LOGIN( 401, "NOT LOGGED IN" ),
    //未认证（签名错误、token错误）
    UNAUTHORIZED( 403, "NOT AUTHENTICATED OR TOKEN INVALID" ),
    //未通过认证
    USER_UNAUTHORIZED( 402, "INCORRECT USERNAME OR PASSWORD" ),
    //接口不存在
    NOT_FOUND( 404, "INTERFACE DOES NOT EXIST" ),
    //服务器内部错误
    INTERNAL_SERVER_ERROR( 500, "SERVER INTERNAL ERROR" );

    private int code;
    private String desc;

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
