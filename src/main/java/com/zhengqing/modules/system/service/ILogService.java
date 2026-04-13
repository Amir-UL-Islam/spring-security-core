package com.zhengqing.modules.system.service;

import com.zhengqing.modules.system.entity.SysLog;
import com.zhengqing.modules.system.dto.input.LogQueryPara;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>  系统管理 - 日志表 服务类 </p>
 *
 * @author: zhengqing
 * @date: 2019-09-18 10:51:57
 */
public interface ILogService {

    /**
     * System Management - Log Table List Paging
     *
     * @param page
     * @param para
     * @return
     */
    Page<SysLog> listPage(Pageable page, LogQueryPara para);

    /**
     * 保存系统管理 - 日志表
     *
     * @param input
     */
    Integer save(SysLog input);

    /**
     * 系统管理 - 日志表列表
     *
     * @param para
     * @return
     */
    List<SysLog> list(LogQueryPara para);

}
