package com.learnJava.mybatis.mapper;

import com.learnJava.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DynamicSQLMapper {

    List<Emp> getEmpByMultipleConditions(Emp emp);

    List<Emp> getEmpByUniqueChoose(Emp emp);

    int multipleDeleteByArray(@Param("eids") Integer[] eids);

    int multipleInsertByList(@Param("emps") List<Emp> emps);
}
