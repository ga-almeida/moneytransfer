package br.com.caseitau.moneytransfer.client.exception;

public class AccountNumberAlreadyExistsExcepetion extends RuntimeException {

    private static final String MESSAGE_ERROR = "Account number already exists.";
    public AccountNumberAlreadyExistsExcepetion() {
        super(MESSAGE_ERROR);
    }
}
