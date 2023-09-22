package com.example.complainboard.controller;

import com.example.complainboard.payload.request.CreateComplainRequestDTO;
import com.example.complainboard.service.ComplainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/complain")
public class ComplainRestController {
    private final ComplainService complainService;

    @PostMapping("/create")
    public ResponseEntity<?> createNewComplain(@RequestBody CreateComplainRequestDTO requestDTO) throws IllegalAccessException {
        complainService.save(requestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
