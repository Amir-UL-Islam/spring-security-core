package com.zhengqing.modules.system.entity;

import com.zhengqing.modules.common.entity.BaseEntity;
import com.zhengqing.modules.common.validator.Create;
import com.zhengqing.modules.common.validator.FieldRepeatValidator;
import com.zhengqing.modules.common.validator.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * <p>  System Management-User Basic Information Table </p>
 *
 * @author: zhengqing
 * @date: 2019-08-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "System Management-User Basic Information Table")
//To sort the annotation groups, you can judge the order through other methods.
//@GroupSequence({FieldRepeatValidator.class,NotNull.class, Default.class})
@FieldRepeatValidator(field = "username", message = "Duplicate account, please re-enter the account!")
@Entity
@Table(name = "t_sys_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key ID groups: The identifier can only be verified to be non-empty when updated.
     */
    @ApiModelProperty(value = "Primary key ID")
    @NotNull(message = "User id cannot be empty", groups = {Update.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * account
     */
    @ApiModelProperty(value = "account")
    @Column(name = "username")
    @NotBlank(message = "Account cannot be empty", groups = {Create.class, Update.class})
    @Length(max = 100, message = "账号不能超过100个字符")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "Account limit: up to 100 characters, including text, letters and numbers")
    private String username;
    /**
     * Login password
     */
    @ApiModelProperty(value = "Login password")
    @Column(name = "password")
    private String password;
    /**
     * Plain text password - used for QQ third-party authorized login
     */
    @ApiModelProperty(value = "clear text password")
    @Column(name = "pwd")
    @NotBlank(message = "Password cannot be empty")
//	@FieldRepeatValidator(className = "com.zhengqing.modules.system.entity.User", field = "pwd", message = "Duplicate password！")
//	@FieldRepeatValidator(className = "com.zhengqing.modules.system.entity.User", field = "pwd", message = "Duplicate password！",groups={FieldRepeatValidator.class})
    private String pwd;
    /**
     * Nick name
     */
    @ApiModelProperty(value = "Nick name")
    @Column(name = "nick_name")
    @NotBlank(message = "Nick name")
    private String nickName;
    /**
     * Gender 0: Male 1: Female
     */
    @ApiModelProperty(value = "Gender 0: Male 1: Female")
    @Column(name = "sex")
    private String sex;
    /**
     * phone number
     */
    @ApiModelProperty(value = "phone number")
    @Column(name = "phone")
    @NotBlank(message = "Mobile phone number cannot be empty")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "Mobile phone number format is wrong")
    private String phone;
    /**
     * Mail
     */
    @ApiModelProperty(value = "Mail")
    @Column(name = "email")
    @NotBlank(message = "Contact email cannot be empty")
    @Email(message = "Email format is wrong")
    private String email;
    /**
     * avatar
     */
    @ApiModelProperty(value = "avatar")
    @Column(name = "avatar")
    private String avatar;
    /**
     * state
     */
    @ApiModelProperty(value = "state")
    @Column(name = "flag")
    private String flag;
    /**
     * salt value
     */
    @ApiModelProperty(value = "salt value")
    @Column(name = "salt")
    private String salt;
    /**
     * token
     */
    @ApiModelProperty(value = "token")
    @Column(name = "token")
    private String token;

    @ApiModelProperty(value = "QQ third-party login Oppen_ID unique identifier")
    @Column(name = "qq_oppen_id")
    private String qqOppenId;

//	@NotBlank(message = "ID number cannot be empty")
//	@IdentityCardNumber(message = "The ID information is incorrect, please check before submitting.")
//	private String clientCardNo;

    protected Serializable pkVal() {
        return this.id;
    }

}
