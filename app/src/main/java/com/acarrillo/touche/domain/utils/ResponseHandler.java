package com.acarrillo.touche.domain.utils;

public interface ResponseHandler<T> {
    void handle(Either<Error, T> result);
}
