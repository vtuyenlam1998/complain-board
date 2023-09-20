package com.example.complainboard.controller;

import com.example.complainboard.model.User;
import com.example.complainboard.payload.request.UserRegisterRequestDTO;
import com.example.complainboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("sign-up")
    public ModelAndView showRegisterPage() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }
    @PostMapping("sign-up")
    public ModelAndView register(@ModelAttribute("user")UserRegisterRequestDTO requestDTO) {
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        userService.register(requestDTO);
        return modelAndView;
    }
}
