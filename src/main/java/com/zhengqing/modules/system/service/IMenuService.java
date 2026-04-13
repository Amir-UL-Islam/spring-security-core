package com.zhengqing.modules.system.service;

import com.zhengqing.modules.system.dto.input.MenuQueryPara;
import com.zhengqing.modules.system.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    Page<Menu> listPage(Pageable page, MenuQueryPara filter);

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

    List<Menu> findByParentId(Integer parentId);

    void deleteById(Integer id);

    Menu selectById(Integer id);
}
