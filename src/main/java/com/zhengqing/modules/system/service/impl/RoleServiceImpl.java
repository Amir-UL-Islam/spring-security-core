package com.zhengqing.modules.system.service.impl;

import com.zhengqing.modules.system.entity.Role;
import com.zhengqing.modules.system.dto.input.RoleQueryPara;
import com.zhengqing.modules.system.repository.RoleRepository;
import com.zhengqing.modules.system.service.IRoleService;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p> System Management-Role Table Service Implementation Class </p>
 *
 * @author: zhengqing
 * @date: 2019-08-20
 */
@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {


    private final RoleRepository roleRepository;

    @Override
    public void listPage(Page<Role> page, RoleQueryPara filter) {
        int pageIndex = Math.max(page.getCurrent() - 1, 0);
        int pageSize = page.getSize();
        page.setRecords(roleRepository.selectRoles(PageRequest.of(pageIndex, pageSize), filter));
    }

    @Override
    public List<Role> list(RoleQueryPara filter) {
        return roleRepository.selectRoles(filter);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Role selectById(Integer id) {
        return null;
    }

    @Override
    public Integer save(Role para) {
        if (para.getId() != null) {
//            roleMapper.updateById(para);
            roleRepository.save(para);
        } else {
//            roleMapper.insert(para);
            roleRepository.save(para);
        }
        return para.getId();
    }

}
