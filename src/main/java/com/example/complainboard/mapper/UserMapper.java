package com.example.complainboard.mapper;

import com.example.complainboard.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users where username = #{username}")
    User findByUsername(String username);
    @Select("SELECT r.name from roles r inner join users u on r.id = u.role_id where u.username = #{username}")
    @ResultType(String.class)
    List<String> findRolesByUsername(@Param("username") String username);
}
