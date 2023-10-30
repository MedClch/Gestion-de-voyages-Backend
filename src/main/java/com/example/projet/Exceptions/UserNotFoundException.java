package com.example.projet.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("Couldn't find the user with ID "+id);
    }
}
