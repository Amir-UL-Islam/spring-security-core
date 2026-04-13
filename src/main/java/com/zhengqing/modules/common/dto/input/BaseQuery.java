package com.zhengqing.modules.common.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  <p> Base class query parameters </p>
 *
 * @description:
 * @author: zhengqing
 * @date: 2019/9/13 0013 1:57
 */
@ApiModel(description = "Base class query parameters")
@Data
public class BaseQuery extends BasePageQuery{
    @ApiModelProperty(value = "User ID")
    private Integer userId;
}
