package com.example.complainboard.conveter;

import com.example.complainboard.model.Role;
import com.example.complainboard.model.User;
import com.example.complainboard.payload.request.UserRegisterRequestDTO;
import com.example.complainboard.payload.response.CurrentUserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserConverter {

    //    Converts data from UserRegisterRequestDTO object to User entity returned to view
    public User convertRegisterRequestDTOToEntity(UserRegisterRequestDTO requestDTO) {
//        Creates an encrypted password variable
        String hashedPassword = null;

//        Check if a password exists, then encrypt the password using the BCrypt algorithm
        if (!requestDTO.getPassword().isEmpty()) {
            hashedPassword = BCrypt.hashpw(requestDTO.getPassword(), BCrypt.gensalt(10));
        }
        //        Use the Build Design Pattern to create a new User object from the UserRegisterRequestDTO object by mapping properties between each other.
//        and save the new password have been encrypt to entity
        return User.builder()
                .username(requestDTO.getUsername())
                .password(hashedPassword)
                .build();
    }

    //    Converts data from CurrentUserResponseDTO object to User entity returned to view
    public CurrentUserResponseDTO convertEntityToUserResponseDTO(User user) {
        return CurrentUserResponseDTO.builder()
                .image(user.getImage())
                .username(user.getUsername())
                .build();
    }

//    Converts data from UserDetails object to CurrentUserResponseDTO returned to view
    public CurrentUserResponseDTO convertUserDetailsToResponseDTO(User user) {

//        By using the stream to go through each element in the list of Authorities using the getAuthority method and put it in the List role one by one.
//        List<String> roles = userDetail.getAuthorities()
//                .stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());

//        Use the Build Design Pattern to create a new CurrentUserResponseDTO object from the UserDetails object by mapping properties between each other.
        return CurrentUserResponseDTO.builder()
                .image(user.getImage())
                .role(user.getRole().getName())
                .username(user.getUsername())
                .build();
    }
}
