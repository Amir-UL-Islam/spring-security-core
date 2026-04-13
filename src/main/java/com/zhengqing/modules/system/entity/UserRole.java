package com.zhengqing.modules.system.entity;

import com.zhengqing.modules.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * <p>  System Management - User Role Association Table  </p>
 *
 * @author: zhengqing
 * @date: 2019-08-20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "System Management - User Role Association Table ")
@Entity
@Table(name = "t_sys_user_role")
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Column(name = "id", nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    @Column(name = "user_id")
    private Integer userId;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    @Column(name = "role_id")
    private Integer roleId;
}
