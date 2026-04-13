package com.zhengqing.modules.system.repository;

import com.zhengqing.modules.system.dto.input.LogQueryPara;
import com.zhengqing.modules.system.entity.SysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LogRepository extends JpaRepository<SysLog, Long>, DefaultRepository<SysLog> {


    /**
     * ListPaging
     *
     * @param page
     * @param filter
     * @return
     */

    default Page<SysLog> selectLogs(Pageable page, @Param("filter") LogQueryPara filter) {
        return findAll(DefaultRepository.buildSpecification(filter), page);
    }

    /**
     * List
     *
     * @param filter
     * @return
     */
    default List<SysLog> selectLogs(@Param("filter") LogQueryPara filter) {
        return findAll(DefaultRepository.buildSpecification(filter));
    }

}
