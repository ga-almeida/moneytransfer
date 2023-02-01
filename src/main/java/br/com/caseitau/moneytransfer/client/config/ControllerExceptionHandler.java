package br.com.caseitau.moneytransfer.client.config;

import br.com.caseitau.moneytransfer.client.exception.AccountNumberAlreadyExistsExcepetion;
import br.com.caseitau.moneytransfer.client.exception.ClientNotFoundException;
import br.com.caseitau.moneytransfer.client.exception.ResponseError;
import br.com.caseitau.moneytransfer.client.exception.ValueIsHigherThanAccountExcepetion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static br.com.caseitau.moneytransfer.client.controller.ResponseEntityTypes.*;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(AccountNumberAlreadyExistsExcepetion.class)
    public ResponseEntity<ResponseError> handleAccountNumberAlreadyExistsExcepetion(AccountNumberAlreadyExistsExcepetion exception) {;
        return conflit(ResponseError.builder()
                .errorCode(CONFLICT.getReasonPhrase())
                .message(exception.getMessage())
                .build());
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ResponseError> handleClientNotFoundException(ClientNotFoundException exception) {;
        return notFound(ResponseError.builder()
                .errorCode(NOT_FOUND.getReasonPhrase())
                .message(exception.getMessage())
                .build());
    }

    @ExceptionHandler(ValueIsHigherThanAccountExcepetion.class)
    public ResponseEntity<ResponseError> handleValueIsHigherThanAccountExcepetion(ValueIsHigherThanAccountExcepetion exception) {;
        return badRequest(ResponseError.builder()
                .errorCode(BAD_REQUEST.getReasonPhrase())
                .message(exception.getMessage())
                .build());
    }
}
