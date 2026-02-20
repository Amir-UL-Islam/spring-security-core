package com.zhengqing.modules.system.service.impl;

import com.zhengqing.modules.system.entity.SysLog;
import com.zhengqing.modules.system.dto.input.LogQueryPara;
import com.zhengqing.modules.system.repository.LogRepository;
import com.zhengqing.modules.system.service.ILogService;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p> 系统管理 - 日志表 服务实现类 </p>
 *
 * @author: zhengqing
 * @date: 2019-09-18 10:51:57
 */
@Service
@Transactional
@RequiredArgsConstructor
public class LogServiceImpl implements ILogService {

    private final LogRepository logRepository;

    @Override
    public void listPage(Page<SysLog> page, LogQueryPara para) {
        int pageIndex = Math.max(page.getCurrent() - 1, 0);
        int pageSize = page.getSize();
        List<SysLog> result = logRepository.selectLogs(PageRequest.of(pageIndex, pageSize), para);
        result.forEach(e -> {
            if (e.getUserId() == 0) {
                e.setUsername("非法人员");
            }
        });
        page.setRecords(result);
    }

    @Override
    public List<SysLog> list(LogQueryPara para) {
        return logRepository.selectLogs(para);
    }

    @Override
    public Integer save(SysLog para) {
        if (para.getId() != null) {
//            logMapper.updateById(para);
            logRepository.save(para);
        } else {
//            logMapper.insert(para);
            logRepository.save(para);
        }
        return para.getId();
    }

}
