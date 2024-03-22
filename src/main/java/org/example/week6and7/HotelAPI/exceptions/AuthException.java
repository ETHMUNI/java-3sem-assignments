package org.example.week6and7.HotelAPI.exceptions;

public class AuthException extends Exception {
    private final int statusCode;

    public AuthException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
