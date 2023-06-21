package com.bezkoder.springjwt.exception;

public class UserAlreadyEnrolledException extends Exception{
    public UserAlreadyEnrolledException(String message){
        super(message);
    }
}
