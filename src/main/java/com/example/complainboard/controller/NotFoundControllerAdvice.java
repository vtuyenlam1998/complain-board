package com.example.complainboard.controller;

import com.example.complainboard.exception.RestrictPermitException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

//This annotation is used to define a global exception handler for controllers in your Spring application. It allows you to centralize exception handling logic and apply it across multiple controllers. In this case, it's used to handle exceptions of the NotFoundException type.
@ControllerAdvice
public class NotFoundControllerAdvice {
//    This annotation marks the handleNotFoundException method as an exception handler for the NotFoundException class or its subclasses. This means that when a NotFoundException is thrown in any controller method, this handler will be invoked to process the exception.
    @ExceptionHandler(RestrictPermitException.class)
//    This method is the actual exception handling logic. It takes a NotFoundException object (ex) as a parameter, which represents the exception that was thrown.
//
//    Inside the method, a new ModelAndView object is created with the view name "error-404." This suggests that when a NotFoundException occurs, the application will render the "error-404" view.
//
//            The "error-404" view is typically a custom error page that you have defined in your application to display a "Not Found" error message or page to the user.
    public ModelAndView handleRestrictPermitException(RestrictPermitException ex) {
        return new ModelAndView("redirect:/error-403");
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException() {
        return new ModelAndView("redirect:/error-404");
    }
}
