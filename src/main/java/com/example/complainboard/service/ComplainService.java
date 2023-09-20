package com.example.complainboard.service;


import com.example.complainboard.model.Complain;
import com.example.complainboard.pageable.MyBatisPageable;
import com.example.complainboard.payload.request.CreateComplainRequestDTO;
import com.example.complainboard.payload.request.EditComplainRequestDTO;
import com.example.complainboard.payload.response.PageResponseDTO;

import java.util.List;

public interface ComplainService {
    List<Complain> findAll();
    Complain findById(Long id);
    Complain save(CreateComplainRequestDTO createComplainRequestDTO);
    Complain update(EditComplainRequestDTO editComplainRequestDTO);
    Complain delete(Long id);
    PageResponseDTO findByPage(MyBatisPageable pageable);
}
