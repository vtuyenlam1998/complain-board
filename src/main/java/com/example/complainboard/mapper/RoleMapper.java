package com.example.complainboard.mapper;

import com.example.complainboard.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMapper {
//    @Select("SELECT * FROM roles where name = #{name} ")
    Role findRoleByName(@Param("name") String name);
//    @Results(id = "roleResultMap", value = {
//            @Result(column = "id", property = "id", id = true),
//            @Result(column = "name", property = "name"),
//            @Result(column = "description", property = "description")
//    })
//
//    @Select("SELECT * FROM roles WHERE id = #{id}")
    Role getRoleById(Long id);
}
