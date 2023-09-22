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

//    This is the method signature. It accepts two parameters:
//    requestDTO: This parameter is annotated with @ModelAttribute("user"), which means that it will be bound to form data sent in the request and associated with the model attribute named "user." UserRegisterRequestDTO likely represents the data submitted by the user during registration.
//            redirectAttributes: This is an instance of RedirectAttributes, which is used to add flash attributes that survive redirects. Flash attributes are typically used to display success or error messages after a redirect.
    public ModelAndView register(@ModelAttribute("user") UserRegisterRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

//        This line queries the userService to check if a user with the provided username already exists. If a user with the same username is found, it indicates that the username is already registered.
        User user = userService.getUserByUsername(requestDTO.getUsername());

//        This conditional block checks if a user with the same username exists. If so:
//        modelAndView.addObject("message","This username has been registered"); adds a message to the modelAndView indicating that the username has already been registered.
//        modelAndView.setViewName("register"); sets the view name to "register," suggesting that the user should stay on the registration page.
//        return modelAndView; returns the modelAndView immediately, preventing further processing and displaying an error message to the user.
        if (user != null) {
            modelAndView.addObject("message","This username has been registered");
            modelAndView.setViewName("register");
            return modelAndView;
        }
//        If the username is not found (i.e., the registration is successful), this line sets the view name to "redirect:/login." This indicates that after a successful registration, the user should be redirected to the login page.
        modelAndView.setViewName("redirect:/login");

//        This line adds a flash attribute named "message" with the value "Register successfully." This attribute will survive the redirect and can be used to display a success message on the login page.
        redirectAttributes.addFlashAttribute("message","Register successfully");
        
//        This line invokes the register method of the userService to perform the actual user registration.
        userService.register(requestDTO);
        return modelAndView;
    }
}
