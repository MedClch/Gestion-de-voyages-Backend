package com.example.projet.Services.Exceptions;

public class DuplicateTicketException extends RuntimeException {
    public DuplicateTicketException(String s) {
            super(s);
    }
}
