package com.zhengqing.modules.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhengqing.modules.system.dto.input.UserQueryPara;
import com.zhengqing.modules.system.dto.model.UserInfoVO;
import com.zhengqing.modules.system.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>  系统管理-用户基础信息表 服务类 </p>
 *
 * @author: zhengqing
 * @date: 2019-08-19
 */
public interface IUserService {

    /**
     * 系统管理-用户基础信息表列表分页
     *
     * @param page
     * @param filter
     * @return
     */
    void listPage(Page<User> page, UserQueryPara filter);

    /**
     * 保存系统管理-用户基础信息表
     *
     * @param input:
     * @return: java.lang.Integer
     */
    Integer save(User input);

    /**
     * 修改用户个人信息
     *
     * @param para:
     * @return: java.lang.Integer
     */
    Integer updatePersonalInfo(User para);

    /**
     * 系统管理-用户基础信息表列表
     *
     * @param filter
     * @return
     */
    List<User> list(UserQueryPara filter);

    /**
     * 通过token获取用户信息
     *
     * @param token:
     * @return: com.zhengqing.modules.system.dto.model.UserInfoVO
     */
    UserInfoVO getCurrentUserInfo(String token);

    /**
     * List paging
     *
     * @param page
     * @param filter
     * @return
     */
    org.springframework.data.domain.Page<User> selectUsers(Pageable page, @Param("filter") UserQueryPara filter);

    /**
     * List
     *
     * @param filter
     * @return
     */
    List<User> selectUsers(@Param("filter") UserQueryPara filter);

    /**
     * Find user information by account
     *
     * @param username:
     * @return: com.zhengqing.modules.system.entity.User
     */
    User selectUserByUsername(@Param("username") String username);

    /**
     * Find user information by token
     *
     * @param token:
     * @return: com.zhengqing.modules.system.entity.User
     */
    User getUserInfoByToken(@Param("token") String token);

    /**
     * Find user information through qq_oppen_id
     *
     * @param qqOppenId:
     * @return: com.zhengqing.modules.system.entity.User
     */
    User getUserInfoByQQ(@Param("qq_oppen_id") String qqOppenId);

    /**
     * Query user collection by role ID
     *
     * @param roleId:
     * @return: java.util.List<Role>
     */
    List<User> selectUserByRoleId(@Param("roleId") Integer roleId);


    List<User> selectList(Object o);

    void deleteById(Integer id);

    User selectById(Integer id);
}
