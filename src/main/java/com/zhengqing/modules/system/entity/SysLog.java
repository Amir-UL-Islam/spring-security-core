package com.zhengqing.modules.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.zhengqing.modules.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>  System Administration - Log Table </p>
 *
 * @author: zhengqing
 * @date: 2019-09-18 10:51:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "System Administration - Log Table")
@TableName("t_sys_log")
@Entity
@Table(name = "t_sys_log")
public class SysLog extends BaseEntity<SysLog> {

    private static final long serialVersionUID = 1L;

    /**
     * PrimaryKeyID
     */
    @ApiModelProperty(value = "PrimaryKeyID")
    @TableId(value = "id", type = IdType.AUTO)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * InterfaceName
     */
    @ApiModelProperty(value = "InterfaceName")
    @TableField("name")
    private String name;
    /**
     * InterfaceAddress
     */
    @ApiModelProperty(value = "InterfaceAddress")
    @TableField("url")
    private String url;
    /**
     * VisitorIP
     */
    @ApiModelProperty(value = "VisitorIP")
    @TableField("ip")
    private String ip;
    /**
     * VisitorID
     */
    @ApiModelProperty(value = "VisitorID")
    @TableField("user_id")
    private Integer userId;
    /**
     * Status
     */
    @ApiModelProperty(value = "Status")
    @TableField("status")
    private Integer status;
    /**
     * InterfaceExecutionTime
     */
    @ApiModelProperty(value = "InterfaceExecutionTime")
    @TableField("execute_time")
    private String executeTime;

    @ApiModelProperty(value = "VisitorName")
    @TableField(exist = false)
    private String username;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
