package com.example.complainboard.controller;

import com.example.complainboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/access-denied")
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView("error-403");
        return modelAndView;
    }

    @GetMapping("/not-found")
    public ModelAndView notFound() {
        ModelAndView modelAndView = new ModelAndView("error-404");
        return modelAndView;
    }

//    @PostMapping("/signup")
//    public ModelAndView signup(@Validated @ModelAttribute("user") UserDto userDto, RedirectAttributes redirectAttributes, BindingResult bindingResult) {
//        if (bindingResult.hasFieldErrors()) {
//            return new ModelAndView("home/login");
//        } else {
//            userService.save(userDto);
//            ModelAndView modelAndView = new ModelAndView("redirect:/login");
//            redirectAttributes.addFlashAttribute("user",userDto);
//            modelAndView.addObject("message", "New user created successfully");
//            return modelAndView;
//        }
//    }
}
