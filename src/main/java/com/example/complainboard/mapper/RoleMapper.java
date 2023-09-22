package com.example.complainboard.mapper;

import com.example.complainboard.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMapper {
    Role findRoleByName(@Param("name") String name);

    Role getRoleById(Long id);
}
