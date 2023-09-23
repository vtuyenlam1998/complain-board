package com.example.complainboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    @RequestMapping("/error-404")
    public ModelAndView handle404() {
        return new ModelAndView("error-404");
    }

    @RequestMapping("/error-403")
    public ModelAndView handle403() {
        return new ModelAndView("error-403");
    }
}
