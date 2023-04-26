package com.learnJava.mybatis.mapper;

import com.learnJava.mybatis.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SelectMapper {

    User selectUserByUserId(@Param("id") Integer id);

    List<User> getAllUser();

    Integer getCount();

    /**
     * 需要将查询结果存为哈希表, 且查询结果只有1条:
     * @return 以属性(字段)为String-key, 属性值(字段值)为Object-值的哈希表
     */
    Map<String, Object> getUserByUserIdToMap(@Param("id") Integer id);

    /**
     * 需要将查询结果存为哈希表, 且查询结果有多条:
     * @return 以MapKey为String-key, 查询结果为Object-值的哈希表
     *
     * 一般MapKey选择主键, 这样就不会因key重复被覆盖
     */
    @MapKey("id")
    Map<String, Object> getAllUserToMap();
}
