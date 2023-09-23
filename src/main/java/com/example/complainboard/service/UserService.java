package com.example.complainboard.service;

import com.example.complainboard.model.Role;
import com.example.complainboard.model.User;
import com.example.complainboard.payload.request.UserRegisterRequestDTO;
import com.example.complainboard.payload.response.CurrentUserResponseDTO;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<String> findRolesByUsername(String username);

    void register(UserRegisterRequestDTO requestDTO) throws IOException;

    CurrentUserResponseDTO getCurrentUser() throws IllegalAccessException;

    User findByComplainId(Long id);

    User getUserByUsername(String username);
}
