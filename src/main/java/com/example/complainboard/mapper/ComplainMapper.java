package com.example.complainboard.mapper;

import com.example.complainboard.model.Complain;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface ComplainMapper {
    @Results(id="complainResultMap",value = {
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "active", column = "active"),
            @Result(property = "timeCreation", column = "time_creation"),
            @Result(property = "user", column = "user_id",
            one = @One(select = "com.example.complainboard.mapper.UserMapper.getUserById"))
    })
    @Select("SELECT * FROM complains where id= #{id}")
    Complain getComplainById(Long id);
    @Select("SELECT * FROM complains")
    @ResultMap("complainResultMap")
    List<Complain> getAllComplains();
    @Select("SELECT * FROM complains")
    @ResultMap("complainResultMap")
    List<Complain> getComplainsByPage(RowBounds rowBounds);

    @Insert("INSERT INTO complains (title,comment,user_id) values (#{title},#{comment},#{user.id})")
    void insertComplain(Complain complain);

    @Update("UPDATE complains SET title = #{title} , comment = #{comment} where id = #{id}")
    void updateComplain(Complain complain);

    @Delete("DELETE from complains where id = #{id}")
    void deleteComplain(Long id);

    @Select("select count(*) from complains")
    long getTotalRecordCount();
}
