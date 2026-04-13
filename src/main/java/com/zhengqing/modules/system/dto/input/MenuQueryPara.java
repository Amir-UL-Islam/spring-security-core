package com.zhengqing.modules.system.dto.input;

import com.zhengqing.modules.common.dto.input.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * System Administration - Menu Table Query parameters
 *
 * @author: zhengqing
 * @description:
 * @date: 2019-08-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "System Administration - Menu Table Query parameters")
public class MenuQueryPara extends BasePageQuery{
    @ApiModelProperty(value = "id")
    private Integer id;
}
