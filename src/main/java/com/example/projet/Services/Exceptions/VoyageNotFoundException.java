package com.example.projet.Services.Exceptions;

public class VoyageNotFoundException extends RuntimeException {
    public VoyageNotFoundException(Long id) {
        super("Couldn't find the trip with ID "+id);
    }
}
