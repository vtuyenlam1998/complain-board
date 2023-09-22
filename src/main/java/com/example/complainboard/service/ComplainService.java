package com.example.complainboard.service;


import com.example.complainboard.model.Complain;
import com.example.complainboard.pageable.MyBatisPageable;
import com.example.complainboard.payload.request.CreateComplainRequestDTO;
import com.example.complainboard.payload.request.EditComplainRequestDTO;
import com.example.complainboard.payload.response.ComplainResponseDTO;
import com.example.complainboard.payload.response.PageResponseDTO;

import java.util.List;

public interface ComplainService {
    List<Complain> findAll();
    ComplainResponseDTO findById(Long id);
    Complain save(CreateComplainRequestDTO createComplainRequestDTO) throws IllegalAccessException;
    Complain update(EditComplainRequestDTO editComplainRequestDTO);
    Complain delete(Long id);
    PageResponseDTO findByPage(MyBatisPageable pageable);
}
