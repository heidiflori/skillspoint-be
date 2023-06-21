package com.bezkoder.springjwt.exception;

public class ReviewNotPermittedException extends Exception{
    public ReviewNotPermittedException(String message){
        super(message);
    }
}
