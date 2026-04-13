package com.zhengqing.modules.common.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *  <p> Basic paging search parameters </p>
 *
 * @description:
 * @author: zhengqing
 * @date: 2019/9/13 0013 1:57
 */
@ApiModel
public class BasePageQuery extends BaseInput {
    @ApiModelProperty(value = "Current page", required = true, position = 0)
    private Integer page;
    @ApiModelProperty(value = "Display quantity per page", required = true, position = 1)
    private Integer limit;

    public Integer getPage() {
        return page == null ? 1 : page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit == null ? Integer.MAX_VALUE : limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
