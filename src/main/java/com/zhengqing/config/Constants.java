package com.zhengqing.config;

import java.util.HashMap;
import java.util.Map;

/**
 *  <p> Global commonly used variables </p>
 *
 * @description :
 * @author : zhengqing
 * @date : 2019/10/12 14:47
 */
public class Constants {

    /**
     * InterfaceUrl
     */
    public static Map<String,String> URL_MAPPING_MAP = new HashMap<>();

    /**
     *  Get the project root directory
     */
    public static String PROJECT_ROOT_DIRECTORY = System.getProperty("user.dir");

    /**
     * Password encryption related
     */
    public static String SALT = "zhengqing";
    public static final int HASH_ITERATIONS = 1;

    /**
     * Request header - token
     */
    public static final String REQUEST_HEADER = "X-Token";

    /**
     * Request header type：
     * application/x-www-form-urlencoded ： FormFormFormat
     * application/json ： json格式
     */
    public static final String REQUEST_HEADERS_CONTENT_TYPE = "application/json";

    /**
     * Login role
     */
    public static final String ROLE_LOGIN = "role_login";

}
