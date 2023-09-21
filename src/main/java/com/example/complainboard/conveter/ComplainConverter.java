package com.example.complainboard.conveter;

import com.example.complainboard.model.Complain;
import com.example.complainboard.payload.request.CreateComplainRequestDTO;
import com.example.complainboard.payload.request.EditComplainRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComplainConverter {

    public Complain convertCreateRequestDTOToEntity(CreateComplainRequestDTO requestDTO) {
        return Complain.builder()
                .title(requestDTO.getTitle())
                .comment(requestDTO.getComment())
                .build();
    }
    public Complain convertEditRequestDTOToEntity(EditComplainRequestDTO requestDTO) {
        return Complain.builder()
                .id(requestDTO.getId())
                .comment(requestDTO.getComment())
                .title(requestDTO.getTitle() )
                .build();
    }

}
