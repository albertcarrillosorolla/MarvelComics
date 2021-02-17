package com.acarrillo.touche.data.exceptions;

public class ApiError extends Error{
    public ApiError(String message)
    {
        super(message);
    }
}
