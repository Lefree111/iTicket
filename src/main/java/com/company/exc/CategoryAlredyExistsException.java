package com.company.exc;

public class CategoryAlredyExistsException extends RuntimeException{
    public CategoryAlredyExistsException(String message) {
        super(message);
    }
}
