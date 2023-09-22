package com.example.complainboard.payload.response;

import com.example.complainboard.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComplainResponseDTO {
    private Long id;
    private String title;
    private String comment;
    private String timeCreation;
    private User user;
}
