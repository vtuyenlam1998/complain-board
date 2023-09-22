package com.example.complainboard.payload.request;

import com.example.complainboard.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterRequestDTO {
    private int id;
    private String username;
    private String password;
    private MultipartFile image;
}
