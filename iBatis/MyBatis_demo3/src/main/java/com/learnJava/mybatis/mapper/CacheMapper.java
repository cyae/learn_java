package com.learnJava.mybatis.mapper;

import com.learnJava.pojo.Emp;
import org.apache.ibatis.annotations.Param;

public interface CacheMapper {
    Emp SessionSelect(@Param("eid") Integer eid);

}
