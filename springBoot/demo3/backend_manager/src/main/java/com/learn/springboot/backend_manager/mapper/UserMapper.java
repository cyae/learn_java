package com.learn.springboot.backend_manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learn.springboot.backend_manager.bean.UserForSQL;

// mybatis写法:
// @Mapper
// public interface UserMapper {
    
//     public List<UserForSQL> getAllUsers();
    
//     @Select("select * from t_user where id = #{id}")
//     public UserForSQL getUserById(int id);
    
//     @Insert("insert into t_user(username, password) values(#{username}, #{password})")
//     // 返回自增主键
//     @Options(useGeneratedKeys = true, keyProperty = "id")
//     public int addUser(UserForSQL user);

//     public int updateUser(UserForSQL user);
    
//     public int deleteUser(int id);
// }

// mybatis-plus写法:
// 启动类有了@MapperScan则无需再@Mapper
// BaseMapper已经提供大量CRUD操作, 如果还需要更多操作, 可以再手动@Select/@Insert...
public interface UserMapper extends BaseMapper<UserForSQL> {}