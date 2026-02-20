package com.zhengqing.modules.system.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zhengqing.modules.system.entity.Menu;
import com.zhengqing.modules.system.dto.input.MenuQueryPara;

import java.util.List;

/**
 * <p>  System Management-Menu Table Service Category </p>
 *
 * @author: zhengqing
 * @date: 2019-08-19
 */
public interface IMenuService {

    /**
     * MenuTree
     *
     * @param :
     * @return: java.util.List<com.zhengqing.modules.system.entity.Menu>
     */
    List<Menu> listTreeMenu();

    /**
     * System Management-Menu Table List Paging
     *
     * @param page
     * @param filter
     * @return
     */
    void listPage(Page<Menu> page, MenuQueryPara filter);

    /**
     * Save system management-menu table
     *
     * @param input
     */
    Integer save(Menu input);

    /**
     * System Management-Menu Table List
     *
     * @param filter
     * @return
     */
    List<Menu> list(MenuQueryPara filter);

    List<Menu> selectList(Wrapper<Menu> parentId);

    void deleteById(Integer id);

    Menu selectById(Integer id);
}
