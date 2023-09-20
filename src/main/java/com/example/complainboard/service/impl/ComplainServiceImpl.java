package com.example.complainboard.service.impl;


import com.example.complainboard.conveter.ComplainConverter;
import com.example.complainboard.mapper.ComplainMapper;
import com.example.complainboard.model.Complain;
import com.example.complainboard.pageable.MyBatisPageable;
import com.example.complainboard.payload.request.CreateComplainRequestDTO;
import com.example.complainboard.payload.request.EditComplainRequestDTO;
import com.example.complainboard.payload.response.PageResponseDTO;
import com.example.complainboard.service.ComplainService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplainServiceImpl implements ComplainService{
    private final ComplainConverter complainConverter;
    private final ComplainMapper complainMapper;

    @Autowired
    public ComplainServiceImpl(ComplainMapper complainMapper, ComplainConverter complainConverter) {
        this.complainConverter = complainConverter;
        this.complainMapper = complainMapper;
    }
    @Override
    public List<Complain> findAll() {
        return complainMapper.getAllComplains();
    }

    @Override
    public Complain findById(Long id) {
        return complainMapper.getComplainById(id);
    }

    @Override
    public Complain save(CreateComplainRequestDTO createComplainRequestDTO) {
        Complain Complain = complainConverter.convertCreateRequestDTOToEntity(createComplainRequestDTO);
        complainMapper.insertComplain(Complain);
        return Complain;
    }

    @Override
    public Complain update(EditComplainRequestDTO editComplainRequestDTO) {
        Complain Complain = complainConverter.convertEditRequestDTOToEntity(editComplainRequestDTO);
        complainMapper.updateComplain(Complain);
        return Complain;
    }

    @Override
    public Complain delete(Long id) {
        Complain Complain = complainMapper.getComplainById(id);
        complainMapper.deleteComplain(id);
        return Complain;
    }

    @Override
    public PageResponseDTO findByPage(MyBatisPageable pageable) {
        int offset = pageable.getPage() * pageable.getSize();
        RowBounds rowBounds = new RowBounds(offset,pageable.getSize());
        List<Complain> complainList = complainMapper.getComplainsByPage(rowBounds);
        long totalRecordCount = complainMapper.getTotalRecordCount();
        long totalPage = (totalRecordCount + pageable.getSize() - 1) / pageable.getSize();
        PageResponseDTO responseDTO = new PageResponseDTO();
        responseDTO.setComplainList(complainList);
        responseDTO.setPageNumber(pageable.getPage());
        responseDTO.setTotalPages(totalPage);
        responseDTO.setHasNext(pageable.getPage()< totalPage - 1);
        responseDTO.setHasPrevious(pageable.getPage()>0);
        return responseDTO;
    }
}
