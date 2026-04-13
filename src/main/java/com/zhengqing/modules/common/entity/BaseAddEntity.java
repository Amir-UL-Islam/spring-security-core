package com.zhengqing.modules.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * <p> Creation date </p>
 *
 * @description:
 * @author: zhengqing
 * @date: 2019/8/18 0018 1:34
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseAddEntity implements Serializable {
    /**
     * Creation date - Present tense indicates active creation
     */
    @ApiModelProperty(value = "Creation date")
    @Column(name = "gmt_create", nullable = false, updatable = false)
    @Past(message = "Creation time must be in the past")
    private Date gmtCreate;

    @PrePersist
    protected void onCreate() {
        this.gmtCreate = new Date();
    }
    /**
     * 创建人
     */
//    @TableField(value = "creator_id", fill = FieldFill.INSERT)
//    private Long creatorId;
    /**
     * 是否可用
     */
//    @TableField(fill = FieldFill.INSERT)
//    private Boolean availableFlag;
}
