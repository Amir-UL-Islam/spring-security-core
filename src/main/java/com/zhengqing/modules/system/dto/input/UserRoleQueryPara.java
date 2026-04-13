package com.zhengqing.modules.system.dto.input;

import com.zhengqing.modules.common.dto.input.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * System Administration - User Role Association Table Query parameters
 *
 * @author: zhengqing
 * @description:
 * @date: 2019-08-20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "System Administration - User Role Association Table Query parameters")
public class UserRoleQueryPara extends BasePageQuery{
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "Role ID")
    private Integer roleId;
    @ApiModelProperty(value = "User IDS")
    private String userIds;
}
