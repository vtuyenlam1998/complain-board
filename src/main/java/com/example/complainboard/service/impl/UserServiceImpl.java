package com.example.complainboard.service.impl;

import com.example.complainboard.conveter.UserConverter;
import com.example.complainboard.mapper.RoleMapper;
import com.example.complainboard.mapper.UserMapper;
import com.example.complainboard.model.Role;
import com.example.complainboard.model.User;
import com.example.complainboard.payload.request.UserRegisterRequestDTO;
import com.example.complainboard.payload.response.CurrentUserResponseDTO;
import com.example.complainboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final UserConverter userConverter;

    @Value("${file-upload}")
    private String fileUpload;

    @Override
    public List<String> findRolesByUsername(String username) {
        return userMapper.findRolesByUsername(username);
    }

//    @Override
//    public void register(UserRegisterRequestDTO requestDTO) {
//        // Extract the image file from the request DTO.
//        MultipartFile multipartFile = requestDTO.getImage();
//
//        // Get the original filename of the image file.
//        String fileName = multipartFile.getOriginalFilename();
//
//        try {
//            // Copy the bytes of the image file to a location on the server.
//            FileCopyUtils.copy(requestDTO.getImage().getBytes(), new File(fileUpload + fileName));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        // Find the "ROLE_USER" role from the database (assuming roleMapper does this).
//        Role role = roleMapper.findRoleByName("ROLE_USER");
//
//        // Convert the UserRegisterRequestDTO into a User entity.
//        User user = userConverter.convertRegisterRequestDTOToEntity(requestDTO);
//
//        // Set the image filename for the user.
//        user.setImage(fileName);
//
//        // Set the user's role to "ROLE_USER".
//        user.setRole(role);
//
//        // Save (insert) the User entity into the database using userMapper.
//        userMapper.save(user);
//    }

    @Override
    public void register(UserRegisterRequestDTO requestDTO) throws IOException {

//        the code checks if an image is present in the requestDTO using requestDTO.getImage().isEmpty(). If there's no image in the requestDTO, the code skips the image processing part and proceeds with user registration.
        if(!requestDTO.getImage().isEmpty()) {

//            If there's an image in requestDTO, the code reads the image data using requestDTO.getImage().getBytes() to get the byte array of the image.
            byte[] imageBytes = requestDTO.getImage().getBytes();

//            it uses Base64.getEncoder().encodeToString(imageBytes) to encode this byte array into a Base64 string.
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // Find the "ROLE_USER" role from the database (assuming roleMapper does this).
            Role role = roleMapper.findRoleByName("ROLE_USER");

            // Convert the UserRegisterRequestDTO into a User entity.
            User user = userConverter.convertRegisterRequestDTOToEntity(requestDTO);

            // Set the image filename for the user.
            user.setImage(base64Image);

            // Set the user's role to "ROLE_USER".
            user.setRole(role);

            // Save (insert) the User entity into the database using userMapper.
            userMapper.save(user);

        }
    }


    @Override
    public CurrentUserResponseDTO getCurrentUser() throws IllegalAccessException {
        // Get the current authentication context.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Initialize a UserDetails object.
        UserDetails userDetails = null;

        // Check if the authentication context is not null and the principal is an instance of UserDetails.
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            userDetails = (UserDetails) authentication.getPrincipal();
        }

        // Ensure that userDetails is not null.
        assert userDetails != null;

        // Get the user entity associated with the authenticated user's username.
        User user = getUserByUsername(userDetails.getUsername());

        // Convert the user details into a CurrentUserResponseDTO.
        return userConverter.convertUserDetailsToResponseDTO(user);
    }

    @Override
    public User findByComplainId(Long id) {
        return userMapper.getUserByComplainId(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
