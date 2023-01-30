package br.com.caseitau.moneytransfer.client.config;

import br.com.caseitau.moneytransfer.client.exception.AccountNumberAlreadyExistsExcepetion;
import br.com.caseitau.moneytransfer.client.exception.ClientNotFoundException;
import br.com.caseitau.moneytransfer.client.exception.ResponseError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static br.com.caseitau.moneytransfer.client.controller.ResponseEntityTypes.conflit;
import static br.com.caseitau.moneytransfer.client.controller.ResponseEntityTypes.notFound;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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
}
