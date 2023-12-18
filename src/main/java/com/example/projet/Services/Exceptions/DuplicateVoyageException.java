package com.example.projet.Services.Exceptions;

public class DuplicateVoyageException extends RuntimeException {
    public DuplicateVoyageException(String message) {
        super(message);
    }
}
