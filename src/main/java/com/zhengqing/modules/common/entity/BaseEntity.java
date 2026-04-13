package com.zhengqing.modules.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import java.time.Instant;
import java.util.Date;

/**
 * <p> 修改时间 </p>
 *
 * @description:
 * @author: zhengqing
 * @date: 2019/8/18 0018 1:30
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity extends BaseAddEntity {
    /**
     * Modification time - past participle indicates passive update
     */
    @ApiModelProperty(value = "Modification Time")
    @Column(name = "gmt_modified", nullable = false, updatable = false)
//    @Future(message = "修改时间必须是将来时间")
    private Date gmtModified;

    @PreUpdate
    protected void onUpdate() {
        this.gmtModified = Date.from(Instant.now());
    }
    /**
     * 修改人
     */
//    @TableField(value = "modifier_id", fill = FieldFill.INSERT_UPDATE)
//    private Long modifierId;
}
