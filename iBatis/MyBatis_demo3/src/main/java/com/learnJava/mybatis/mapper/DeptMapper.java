package com.learnJava.mybatis.mapper;

import com.learnJava.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeptMapper {

    Dept getDeptByEmpDid(@Param("did") Integer did);

    Dept getDeptAndEmpByDid(@Param("did") Integer did);
}
