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

//    This method takes a Complain entity as input and returns a ComplainResponseDTO object as output, which represents a response DTO for a complaint.
    public ComplainResponseDTO convertEntityToResponseDTO (Complain complain) {

//        This line extracts the timeCreation field from the Complain entity, which is likely a LocalDateTime representing the creation time of the complaint.
        LocalDateTime timeCreation = complain.getTimeCreation();

//        This line formats the timeCreation using a LocalDateTimeFormater (presumably a custom formatter) and stores the formatted result in the newTimer string. The purpose here is likely to format the LocalDateTime as a human-readable string before including it in the DTO.
        String newTimer = LocalDateTimeFormater.formatTime(timeCreation);

//        this method takes a Complain entity as input, extracts specific fields from it, formats the timeCreation field, and uses these fields to construct a ComplainResponseDTO object, which represents the complaint in a response-friendly format. This is useful when you want to convert an entity into a DTO for sending as a response, typically in a RESTful API scenario.
        return ComplainResponseDTO.builder()
                .id(complain.getId())
                .timeCreation(newTimer)
                .comment(complain.getComment())
                .title(complain.getTitle())
                .user(complain.getUser())
                .build();
    }

//    This method takes a list of Complain entities as input and returns a list of ComplainResponseDTO objects.
public List<ComplainResponseDTO> convertListEntityToListDTO(List<Complain> complains) {

//        This line converts the input list complains into a stream of Complain entities. A stream allows for convenient processing of elements in a collection.
    return complains.stream()

//        This is an intermediate operation on the stream. It uses the map function to transform each Complain entity into a ComplainResponseDTO by calling the convertEntityToResponseDTO method. The this::convertEntityToResponseDTO syntax is a method reference, which is a shorthand way to refer to a method of the current class.
            .map(this::convertEntityToResponseDTO)

//        This terminal operation collects the mapped ComplainResponseDTO objects from the stream into a new List<ComplainResponseDTO>. It uses the Collectors.toList() collector to gather the elements into a list.
            .collect(Collectors.toList());
}
}
