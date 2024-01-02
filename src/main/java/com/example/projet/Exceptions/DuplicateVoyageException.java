package com.example.projet.Exceptions;

public class DuplicateVoyageException extends RuntimeException {
    public DuplicateVoyageException(String message) {
        super(message);
    }
}
