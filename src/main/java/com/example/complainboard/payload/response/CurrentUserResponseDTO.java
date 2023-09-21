package com.example.complainboard.payload.response;

import com.example.complainboard.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentUserResponseDTO {
    private String username;
    private String role;
}
