package com.company.exc;

public class PhoneNumberAlreadyExistsExeption extends RuntimeException{
    public PhoneNumberAlreadyExistsExeption(String message) {
        super(message);
    }
}
