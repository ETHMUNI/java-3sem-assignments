package org.example.week5.Exercise_wed_thur.ApiException;

/**
 * Purpose of this class is to
 * Author: Thomas Hartmann
 */
public class ApiException extends RuntimeException {
    private int statusCode;
    public ApiException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
    public int getStatusCode() {
        return statusCode;
    }
}
