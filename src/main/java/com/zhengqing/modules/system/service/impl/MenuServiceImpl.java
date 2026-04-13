package com.zhengqing.modules.system.service.impl;

import com.zhengqing.modules.system.dto.input.MenuQueryPara;
import com.zhengqing.modules.system.entity.Menu;
import com.zhengqing.modules.system.repository.MenuRepository;
import com.zhengqing.modules.system.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<Menu> listPage(Pageable page, MenuQueryPara filter) {
        return menuRepository.selectMenus(page, filter);
    }

    @Override
    public List<Menu> list(MenuQueryPara filter) {
        return menuRepository.selectMenus(filter);
    }

    @Override
    public List<Menu> findByParentId(Integer parentId) {
        return menuRepository.findByParentId(String.valueOf(parentId));
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
       return menuRepository.save(para).getId();
    }

}
