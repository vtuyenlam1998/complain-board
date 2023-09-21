package com.example.complainboard.mapper;

import com.example.complainboard.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {


    @Select("SELECT * FROM users where username = #{username}")
    @Results(id="userResultMap",value = {
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "active", column = "active"),
            @Result(property = "role", column = "role_id",
                    one = @One(select = "com.example.complainboard.mapper.RoleMapper.getRoleById"))
    })
    User findByUsername(String username);

    @Select("SELECT r.name from roles r inner join users u on r.id = u.role_id where u.username = #{username}")
    @ResultType(String.class)
    List<String> findRolesByUsername(@Param("username") String username);

    @Insert("insert into users(username,password,role_id) VALUES (#{username}, #{password}, #{role.id})")
    void save(User user);

    @Select("SELECT * FROM users where id = #{id}")
    @ResultMap("userResultMap")
    User getUserById(Long id);

    @Select("SELECT u.* FROM users u inner join complains c on u.id = c.user_id where c.id = #{id}")
    User getUserByComplainId(Long id);
}
