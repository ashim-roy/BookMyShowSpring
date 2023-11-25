package com.scaler.bookmyshow.exceptions;

public class InvalidUserException extends Exception{
    public InvalidUserException(String message) { //     This constructor is used to create an instance of the InvalidUserException class with a specific error message
        super(message);   //super(message) call invokes the constructor of the superclass (Exception in this case) with the provided message.
    }
}
        