package com.example.complainboard.payload.response;

import com.example.complainboard.model.Complain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponseDTO {
    List<ComplainResponseDTO> complainList;
    long pageNumber;
    long totalPages;
    boolean hasNext;
    boolean hasPrevious;
}
