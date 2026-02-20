package com.zhengqing.modules.system.repository;

import com.zhengqing.modules.system.dto.input.LogQueryPara;
import com.zhengqing.modules.system.entity.SysLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LogRepository extends JpaRepository<SysLog, Long> {


    /**
     * ListPaging
     *
     * @param page
     * @param filter
     * @return
     */
    List<SysLog> selectLogs(Pageable page, @Param("filter") LogQueryPara filter);

    /**
     * List
     *
     * @param filter
     * @return
     */
    List<SysLog> selectLogs(@Param("filter") LogQueryPara filter);

}
