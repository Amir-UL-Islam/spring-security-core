package com.zhengqing.modules.system.entity;

import com.zhengqing.modules.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * <p>  System Management - Role-Menu Association Table  </p>
 *
 * @author: zhengqing
 * @date: 2019-08-20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "System Management - Role-Menu Association Table")
@Entity
@Table(name = "t_sys_role_menu")
public class RoleMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * PrimaryKey
     */
    @ApiModelProperty(value = "Primary Key")
    @Column(name = "id", nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * RoleID
     */
    @ApiModelProperty(value = "Role ID")
    @Column(name = "role_id")
    private Integer roleId;
    /**
     * MenuID
     */
    @ApiModelProperty(value = "Menu ID")
    @Column(name = "menu_id")
    private Integer menuId;

}
