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

    Complain getComplainById(Long id);

    List<Complain> getAllComplains();

    List<Complain> getComplainsByPage(RowBounds rowBounds);

    void insertComplain(Complain complain);

    void updateComplain(Complain complain);

    void deleteComplain(Long id);

    long getTotalRecordCount();
}
