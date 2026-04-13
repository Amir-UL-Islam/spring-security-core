package com.zhengqing.modules.system.dto.output;

import com.google.common.collect.Lists;
import com.zhengqing.modules.system.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 *  <p> User tree nodes </p>
 *
 * @description :
 * @author : zhengqing
 * @date : 2019/8/20 19:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserTreeNode extends User {

    List<UserTreeNode> children = Lists.newArrayList();

}
