package com.example.complainboard.controller;

import com.example.complainboard.model.User;
import com.example.complainboard.payload.request.UserRegisterRequestDTO;
import com.example.complainboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/sign-up")
    public ModelAndView showRegisterPage() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }
    @PostMapping("/sign-up")
    public ModelAndView register(@ModelAttribute("user") UserRegisterRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getUserByUsername(requestDTO.getUsername());
        if (user != null) {
            modelAndView.addObject("message","This username has been registered");
            modelAndView.setViewName("register");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/login");
        redirectAttributes.addFlashAttribute("message","Register successfully");
        userService.register(requestDTO);
        return modelAndView;
    }
}
