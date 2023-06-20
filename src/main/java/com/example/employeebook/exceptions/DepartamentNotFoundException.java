package com.example.employeebook.exceptions;

public class DepartamentNotFoundException extends RuntimeException{
    public DepartamentNotFoundException(String message) {
        super(message);
    }
}
