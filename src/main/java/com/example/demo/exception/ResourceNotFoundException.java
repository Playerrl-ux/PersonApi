package com.example.demo.exception;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(String message){
        super(message);
    }

    public String getMessage(){
        return super.getMessage();
    }
}
