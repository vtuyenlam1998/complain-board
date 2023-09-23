package com.example.complainboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller

//This annotation typically generates a constructor for the class that automatically injects dependencies based on the final fields. In this case, it's used to inject dependencies required for the controller.
@RequiredArgsConstructor
public class AuthController {

    //This method handles HTTP GET requests to the "/log-out" endpoint, typically used for user logout.
    //It returns a ModelAndView object, which is a Spring class used to combine both model data and view information.
    //In this case, it returns a ModelAndView with the view name "login," which suggests that after logging out, the user is redirected to the login page.
    @GetMapping("/logout")
    public ModelAndView logout() {
        return new ModelAndView("login");
    }

    //This method handles HTTP GET requests to the "/login" endpoint, which is typically used for user login.
    @GetMapping("/login")

    //It accepts an optional query parameter named "status" using the @RequestParam annotation. This parameter might be used to pass additional information about the login status.
    public ModelAndView login(@RequestParam(required = false, name = "status") String status) {

    //It returns a ModelAndView object initialized with the view name "login."
        ModelAndView modelAndView = new ModelAndView("login");

    //If the "status" parameter is present and equal to "incorrectAccount," it adds an attribute named "loginFailed" with the value "Wrong Information" to the model. This suggests that if the login attempt failed (due to incorrect credentials), an error message is displayed to the user on the login page.
        if (status != null && status.equals("incorrectAccount")) {
            modelAndView.addObject("loginFailed", "Wrong Information");
            return modelAndView;
        }
        return modelAndView;
    }

}
