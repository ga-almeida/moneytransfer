package br.com.caseitau.moneytransfer.client.config;

import br.com.caseitau.moneytransfer.client.exception.AccountNumberAlreadyExistsExcepetion;
import br.com.caseitau.moneytransfer.client.exception.ResponseError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static br.com.caseitau.moneytransfer.client.controller.ResponseEntityTypes.conflit;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class ControllerExceptionHandler {
    @ExceptionHandler(AccountNumberAlreadyExistsExcepetion.class)
    public ResponseEntity<ResponseError> handleAccountNumberAlreadyExistsExcepetion(AccountNumberAlreadyExistsExcepetion exception) {;
        return conflit(ResponseError.builder()
                .errorCode(NOT_FOUND.getReasonPhrase())
                .message(exception.getMessage())
                .build());
    }
}
