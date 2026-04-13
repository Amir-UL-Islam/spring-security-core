package com.zhengqing.modules.system.service.impl;

import com.zhengqing.modules.system.entity.SysLog;
import com.zhengqing.modules.system.dto.input.LogQueryPara;
import com.zhengqing.modules.system.repository.LogRepository;
import com.zhengqing.modules.system.service.ILogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<SysLog> listPage(Pageable page, LogQueryPara para) {
        Page<SysLog> result = logRepository.selectLogs(page, para);
        result.forEach(e -> {
            if (e.getUserId() == 0) {
                e.setUsername("非法人员");
            }
        });
        return result;
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
