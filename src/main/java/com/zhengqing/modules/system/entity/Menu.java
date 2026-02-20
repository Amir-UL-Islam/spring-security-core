package com.zhengqing.modules.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.zhengqing.modules.common.entity.BaseEntity;
import com.zhengqing.modules.common.validator.FieldRepeatValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>  System Management-Permission Menu Table  </p>
 *
 * @author: zhengqing
 * @date: 2019-08-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "System Management-Menu Table")
@TableName("t_sys_menu")
@FieldRepeatValidator(field = "resources", message = "Menu coding is repeated!")
@Entity
@Table(name = "t_sys_menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * PrimaryKey
     */
	@ApiModelProperty(value = "Primary Key")
	@TableId(value="id", type= IdType.AUTO)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    /**
     * ParentMenuID
     */
	@ApiModelProperty(value = "Previous menu ID")
	@TableField("parent_id")
	private String parentId;
	/**
	 * url
	 */
	@ApiModelProperty(value = "url")
	@TableField("url")
	private String url;
    /**
     * Menu encoding
     */
	@ApiModelProperty(value = "Menu encoding")
	@TableField("resources")
	@NotBlank(message = "Menu code cannot be empty")
	@Length(max = 100, message = "Menu encoding cannot exceed 100 characters")
	private String resources;
    /**
     * Menu name
     */
	@ApiModelProperty(value = "Menu name")
	@TableField("title")
	@NotBlank(message = "Menu name cannot be empty")
	private String title;
    /**
     * Menu Level
     */
	@ApiModelProperty(value = "MenuLevel")
	@TableField("level")
	private Integer level;
    /**
     * Sort
     */
	@ApiModelProperty(value = "Sort")
	@TableField("sort_no")
	private Integer sortNo;
    /**
     * MenuIcon
     */
	@ApiModelProperty(value = "Menu Icon")
	@TableField("icon")
	private String icon;
    /**
     * Type menu、button
     */
	@ApiModelProperty(value = "Type menu、button")
	@TableField("type")
	@NotBlank(message = "Type cannot be empty")
	private String type;
    /**
     * Remark
     */
	@ApiModelProperty(value = "Remark")
	@TableField("remarks")
	private String remarks;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
