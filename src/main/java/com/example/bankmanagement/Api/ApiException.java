package com.example.bankmanagement.Api;

public class ApiException extends RuntimeException{

    public ApiException(String msg)
    {
        super(msg);
    }
}
