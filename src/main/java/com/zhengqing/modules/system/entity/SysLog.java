package com.zhengqing.modules.system.entity;

import com.zhengqing.modules.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * <p>  System Administration - Log Table </p>
 *
 * @author: zhengqing
 * @date: 2019-09-18 10:51:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "System Administration - Log Table")
@Entity
@Table(name = "t_sys_log")
public class SysLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * PrimaryKeyID
     */
    @ApiModelProperty(value = "PrimaryKeyID")
    @Column(name = "id", nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * InterfaceName
     */
    @ApiModelProperty(value = "InterfaceName")
    @Column(name = "name")
    private String name;
    /**
     * InterfaceAddress
     */
    @ApiModelProperty(value = "InterfaceAddress")
    @Column(name = "url")
    private String url;
    /**
     * VisitorIP
     */
    @ApiModelProperty(value = "VisitorIP")
    @Column(name = "ip")
    private String ip;
    /**
     * VisitorID
     */
    @ApiModelProperty(value = "VisitorID")
    @Column(name = "user_id")
    private Integer userId;
    /**
     * Status
     */
    @ApiModelProperty(value = "Status")
    @Column(name = "status")
    private Integer status;
    /**
     * InterfaceExecutionTime
     */
    @ApiModelProperty(value = "InterfaceExecutionTime")
    @Column(name = "execute_time")
    private String executeTime;

    @ApiModelProperty(value = "VisitorName")
    @Transient
    private String username;
}
