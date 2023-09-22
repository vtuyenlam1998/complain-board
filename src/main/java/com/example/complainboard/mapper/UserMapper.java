package com.example.complainboard.mapper;

import com.example.complainboard.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    User findByUsername(String username);

    List<String> findRolesByUsername(@Param("username") String username);

    void save(User user);

    User getUserById(Long id);

    User getUserByComplainId(Long id);
}
