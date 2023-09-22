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

//This annotation combines the functionality of @Controller and @ResponseBody. It tells Spring that the class should be treated as a RESTful controller, and the return values of its methods should be automatically serialized into JSON or XML (based on content negotiation).
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/complain")
public class ComplainRestController {

    private final ComplainService complainService;

//    This annotation maps HTTP POST requests to the "/api/complain/create" endpoint. It means that this method will handle POST requests to create a new complaint.
    @PostMapping("/create")

//    This is the method signature. It accepts a JSON request body (sent as the requestDTO) and may throw IllegalAccessException.
//This annotation is used to indicate that the method parameter (requestDTO) should be bound to the JSON request body of the incoming POST request. It deserializes the JSON data into the CreateComplainRequestDTO object.
    public ResponseEntity<?> createNewComplain(@RequestBody CreateComplainRequestDTO requestDTO) throws IllegalAccessException {
//        This line invokes the save method of the complainService and passes the requestDTO. It is likely that the save method processes the request data and saves it in the database.
        complainService.save(requestDTO);
//        After successfully creating the complaint, this line creates a ResponseEntity object with an HTTP status code of HttpStatus.CREATED (HTTP 201). It is a common convention in RESTful APIs to return a 201 status code upon successful resource creation.
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
