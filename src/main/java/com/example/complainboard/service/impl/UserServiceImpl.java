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

    @Override
    public void register(UserRegisterRequestDTO requestDTO) {
        MultipartFile multipartFile = requestDTO.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(requestDTO.getImage().getBytes(),new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Role role = roleMapper.findRoleByName("ROLE_USER");
        User user = userConverter.convertRegisterRequestDTOToEntity(requestDTO);
        user.setImage(fileName);
        user.setRole(role);
        userMapper.save(user);
    }

    @Override
    public CurrentUserResponseDTO getCurrentUser() throws IllegalAccessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            userDetails = (UserDetails) authentication.getPrincipal();
        }
        assert userDetails != null;
        User user = getUserByUsername(userDetails.getUsername());
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
