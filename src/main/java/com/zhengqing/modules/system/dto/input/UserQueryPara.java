package com.zhengqing.modules.system.dto.input;

import com.zhengqing.modules.common.dto.input.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * System Management - Query parameters in the basic user information table
 *
 * @author: zhengqing
 * @description:
 * @date: 2019-08-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "System Management - Query parameters in the basic user information table")
public class UserQueryPara extends BasePageQuery{

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "Username")
    private String username;

    @ApiModelProperty(value = "Account - Modify the use of personal information")
    private String account;

    @ApiModelProperty(value = "password")
    private String password;

    @ApiModelProperty(value = "QQ third-party login authorization after successful authentication")
    private String openId;

    @ApiModelProperty(value = "QQ third-party login token after successful authorization authentication")
    private String accessToken;

}
