package com.example.complainboard.exception;

public class RestrictPermitException extends RuntimeException {
    public RestrictPermitException(String message) {
//        Inside the constructor, it calls the constructor of the parent class RuntimeException (which is a built-in exception class in Java) and passes the provided error message to it using the super keyword. This sets the error message for the exception.
        super(message);
    }

//    By creating a custom NotFoundException class that extends RuntimeException, you can throw instances of this exception in your code when you encounter situations where a resource (such as a database record or an object) is not found, and you want to signal this error condition. For example, in a Spring MVC application, you might throw this exception when attempting to retrieve an entity that doesn't exist in the database.
//
//    Later, you can catch and handle this exception in appropriate exception handlers or controller advice classes, as shown in your previous code examples. This allows you to provide meaningful error messages or responses to clients when a resource is not found.
}
