package com.zhengqing.modules.system.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhengqing.modules.system.dto.input.MenuQueryPara;
import com.zhengqing.modules.system.entity.Menu;
import com.zhengqing.modules.system.repository.MenuRepository;
import com.zhengqing.modules.system.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * <p> System Management-Menu Table Service Implementation Class </p>
 *
 * @author: zhengqing
 * @date: 2019-08-19
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MenuServiceImpl implements IMenuService {

    private final MenuRepository menuRepository;

    @Override
    public List<Menu> listTreeMenu() {
        return menuRepository.findAll();
    }

    @Override
    public void listPage(Page<Menu> page, MenuQueryPara filter) {
        int pageIndex = Math.max(page.getCurrent() - 1, 0);
        int pageSize = page.getSize();
        page.setRecords(menuRepository.selectMenus(PageRequest.of(pageIndex, pageSize), filter));
    }

    @Override
    public List<Menu> list(MenuQueryPara filter) {
        return menuRepository.selectMenus(filter);
    }

    @Override
    public List<Menu> selectList(Wrapper<Menu> parentId) {
        return Collections.emptyList();
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Menu selectById(Integer id) {
        return null;
    }

    @Override
    public Integer save(Menu para) {
        if (para.getId() != null) {
//            menuMapper.updateById(para);
            menuRepository.save(para);
        } else {
//            menuMapper.insert(para);
            menuRepository.save(para);
        }
        return para.getId();
    }

}
