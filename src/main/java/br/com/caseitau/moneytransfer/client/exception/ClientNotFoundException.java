package br.com.caseitau.moneytransfer.client.exception;

public class ClientNotFoundException extends RuntimeException {

    private static final String MESSAGE_ERROR = "Client not exists.";
    public ClientNotFoundException() {
        super(MESSAGE_ERROR);
    }
}
