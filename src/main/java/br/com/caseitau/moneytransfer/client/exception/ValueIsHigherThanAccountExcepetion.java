package br.com.caseitau.moneytransfer.client.exception;

public class ValueIsHigherThanAccountExcepetion extends RuntimeException {

    private static final String MESSAGE_ERROR = "Transfer value is higher than the account.";

    public ValueIsHigherThanAccountExcepetion() {
        super(MESSAGE_ERROR);
    }
}
