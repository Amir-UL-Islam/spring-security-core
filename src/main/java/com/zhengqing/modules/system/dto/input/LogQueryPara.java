package com.zhengqing.modules.system.dto.input;

import com.zhengqing.modules.common.dto.input.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * System Administration - Log table query parameters
 *
 * @author: zhengqing
 * @description:
 * @date: 2019-09-18 10:51:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "System Administration - Log table query parameters")
public class LogQueryPara extends BasePageQuery{
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "Interviewer")
    private String username;
    @ApiModelProperty(value = "Visit URL")
    private String url;
    @ApiModelProperty(value = "Access start time")
    private Date startTime;
    @ApiModelProperty(value = "End of visit")
    private Date endTime;
}
