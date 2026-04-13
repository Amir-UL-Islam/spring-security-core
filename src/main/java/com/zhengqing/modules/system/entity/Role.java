package com.zhengqing.modules.system.entity;

import com.zhengqing.modules.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * <p>  System Administration-Role Table  </p>
 *
 * @author: zhengqing
 * @date: 2019-08-20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "System Administration-Role Table")
@Entity
@Table(name = "t_sys_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * PrimaryKeyID
     */
    @ApiModelProperty(value = "Primary key ID")
    @Column(name = "id", nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * RoleCoding
     */
    @ApiModelProperty(value = "Role Coding")
    @Column(name = "code")
    @NotBlank(message = "Role code cannot be empty")
    @Length(max = 20, message = "The character code cannot exceed 20 characters")
    private String code;
    /**
     * Character name
     */
    @ApiModelProperty(value = "Character name")
    @Column(name = "name")
    @NotBlank(message = "Role name cannot be empty")
    private String name;
    /**
     * Role description
     */
    @ApiModelProperty(value = "Role description")
    @Column(name = "remarks")
    private String remarks;

}
