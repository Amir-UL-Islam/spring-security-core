package com.zhengqing.modules.system.dto.output;

import com.zhengqing.modules.system.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 *<p> System Administration - Role Table Output Content </p>
 *
 * @author：  zhengqing <br/>
 * @date：  2019/8/20$ 16:59$ <br/>
 * @version：  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "System Administration - Role Table output")
public class RoleView extends Role {

    @ApiModelProperty(value = "Whether the system user is associated")
    private String isRelatedSysUser;

    @ApiModelProperty(value = "Whether the system menu is linked")
    private String isRelatedSysMenu;

    @ApiModelProperty(value = "Whether the WeChat account has been linked")
    private String isRelatedWxAccount;

}
