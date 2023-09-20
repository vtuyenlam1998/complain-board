package com.example.complainboard.conveter;

import com.example.complainboard.model.User;
import com.example.complainboard.payload.request.UserRegisterRequestDTO;
import com.example.complainboard.payload.response.CurrentUserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {
    public User convertRegisterRequestDTOToEntity(UserRegisterRequestDTO requestDTO) {
        String hashedPassword = null;
        if (!requestDTO.getPassword().isEmpty()) {
            hashedPassword = BCrypt.hashpw(requestDTO.getPassword(), BCrypt.gensalt(10));
        }
        return User.builder()
                .username(requestDTO.getUsername())
                .password(hashedPassword)
                .build();
    }

    public CurrentUserResponseDTO convertEntityToUserResponseDTO(User user) {
        return CurrentUserResponseDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public CurrentUserResponseDTO convertUserDetailsToResponseDTO(UserDetails userDetails) {
        return CurrentUserResponseDTO.builder()
                .password(userDetails.getPassword())
                .username(userDetails.getUsername())
                .build();
    }
}
