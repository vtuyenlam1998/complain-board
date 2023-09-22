package com.example.complainboard.conveter;

import com.example.complainboard.model.Complain;
import com.example.complainboard.payload.request.CreateComplainRequestDTO;
import com.example.complainboard.payload.request.EditComplainRequestDTO;
import com.example.complainboard.payload.response.ComplainResponseDTO;
import com.example.complainboard.util.LocalDateTimeFormater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public ComplainResponseDTO convertEntityToResponseDTO (Complain complain) {
        LocalDateTime timeCreation = complain.getTimeCreation();
        String newTimer = LocalDateTimeFormater.formatTime(timeCreation);
        return ComplainResponseDTO.builder()
                .id(complain.getId())
                .timeCreation(newTimer)
                .comment(complain.getComment())
                .title(complain.getTitle())
                .user(complain.getUser())
                .build();
    }

    public List<ComplainResponseDTO> convertListEntityToListDTO(List<Complain> complains) {
        return complains.stream().map(this::convertEntityToResponseDTO).collect(Collectors.toList());
    }
}
