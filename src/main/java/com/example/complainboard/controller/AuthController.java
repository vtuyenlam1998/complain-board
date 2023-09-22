package com.example.complainboard.controller;

import com.example.complainboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {

    @GetMapping("/log-out")
    public ModelAndView logout() {
        return new ModelAndView("login");
    }
    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false,name = "status") String status) {
        ModelAndView modelAndView = new ModelAndView("login");
        if(status!= null && status.equals("incorrectAccount")){
            modelAndView.addObject("loginFailed","Wrong Information");
            return modelAndView;
        }
        return modelAndView;
    }

    @GetMapping("/access-denied")
    public ModelAndView accessDenied() {
        return new ModelAndView("error-403");
    }

    @GetMapping("/not-found")
    public ModelAndView notFound() {
        return new ModelAndView("error-404");
    }

}
