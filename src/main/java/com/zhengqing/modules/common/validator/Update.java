package com.zhengqing.modules.common.validator;

import javax.validation.groups.Default;

/**
 * <p> Verification using groups </p>
 *
 * @author : zhengqing
 * @description : The same object needs to be reused. For example, UserDTO needs to verify userId when updating, but does not need to verify userId when saving. In both cases, username must be verified, then groups are used.
 * @Validated declares the verification group where verification is required ` update(@RequestBody @Validated(Update.class) UserDTO userDTO) `
 * Define the grouping type of groups = {} on the field in the DTO ` @NotNull(message = "User ID cannot be empty", groups = Update.class) or groups = {Create.class, Update.class}
 * private Long userId; `
 * [Note] Note: When declaring groups, try to add extend javax.validation.groups.Default. Otherwise, when you declare @Validated(Update.class), the verification group @Email (message = "Email format is incorrect") will appear when you did not add groups = {} by default, and will not be verified, because the default verification group is groups = {Default.class}.
 * @date : 2019/9/9 16:51
 */
public interface Update extends Default {
}
