package com.zhengqing.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 *  <p> MyProperties </p>
 *
 * @description :
 * @author : zhengqing
 * @date : 2019/8/19 9:07
 */
@Data
@ConfigurationProperties(prefix = "zhengqing", ignoreUnknownFields = false)
public class MyProperties {

    /**
     * SWAGGER参数
     */
    private final Swagger swagger = new Swagger();
    /**
     * 安全认证
     */
    private final Auth auth = new Auth();

    /**
     * SWAGGER interface document parameters
     */
    @Data
    public static class Swagger {
        private String title;
        private String description;
        private String version;
        private String termsOfServiceUrl;
        private String contactName;
        private String contactUrl;
        private String contactEmail;
        private String license;
        private String licenseUrl;
    }

    @Data
    public static class Auth {
        /**
         * Token expiration time (minutes)
         */
        private Integer tokenExpireTime;
        /**
         * The user chooses to save the login status corresponding to the TOKEN expiration time (days)
         */
        private Integer saveLoginTime;
        /**
         *Limit the number of user login errors (times)
         */
        private Integer loginTimeLimit;
        /**
         * How many minutes does it take to continue logging in after the number of errors exceeds the number (minutes)
         */
        private Integer loginAfterTime;
        /**
         * Ignore secure certified URLs
         */
        private List<String> ignoreUrls;
    }

}
