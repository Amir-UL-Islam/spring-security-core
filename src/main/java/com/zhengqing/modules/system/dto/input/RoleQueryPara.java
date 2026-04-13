package com.zhengqing.modules.system.dto.input;

import com.zhengqing.modules.common.dto.input.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * System Administration - Role Table Query parameters
 *
 * @author: zhengqing
 * @description:
 * @date: 2019-08-20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "System Administration - Role Table Query parameters")
public class RoleQueryPara extends BasePageQuery{
    @ApiModelProperty(value = "Role ID")
    private Integer id;
    @ApiModelProperty(value = "Character name")
    private String name;
}
