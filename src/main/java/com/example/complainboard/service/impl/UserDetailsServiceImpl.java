package com.example.complainboard.service.impl;

import com.example.complainboard.mapper.UserMapper;
import com.example.complainboard.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the User entity from the database based on the provided username.
        User user = userMapper.findByUsername(username);

        // Check if the user was found in the database.
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database!");
        }

        // Fetch the roles associated with the user from the database.
        List<String> roles = userMapper.findRolesByUsername(username);

        // Create a list of GrantedAuthority objects based on the user's roles.
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String role : roles) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantedAuthorities.add(authority);
        }

        // Create a UserDetails object with the user's username, password, and authorities (roles).
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities);

        return userDetails;
    }
}
