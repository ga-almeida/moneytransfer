package br.com.caseitau.moneytransfer.client.controller;

import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.*;

public final class ResponseEntityTypes {

    public static <T> ResponseEntity<T> created(T body) {
        return ResponseEntity.status(CREATED).body(body);
    }

    public static <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity.status(OK).body(body);
    }

    public static <T> ResponseEntity<T> conflit(T responseError) {
        return ResponseEntity.status(CONFLICT).body(responseError);
    }

}
