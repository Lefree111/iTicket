package com.company.exc;

public class EmailAlreadyExistsExeption extends RuntimeException{
    public EmailAlreadyExistsExeption(String message) {
        super(message);
    }
}
