package com.learn.demo3.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.learn.demo3.model.Book;

@Mapper
public interface BookMapper {
    
    @Select("select * from t_book where id = #{id}")
    public Book getBookById(@Param("id") int id); 
}
