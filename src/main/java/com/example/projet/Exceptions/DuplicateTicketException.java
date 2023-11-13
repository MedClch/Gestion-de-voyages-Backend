package com.example.projet.Exceptions;

public class DuplicateTicketException extends RuntimeException {
    public DuplicateTicketException(String s) {
            super(s);
    }
}
