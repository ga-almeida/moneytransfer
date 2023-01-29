package br.com.caseitau.moneytransfer.client.controller.createClient;

import jakarta.validation.constraints.NotNull;

public class CreateClientRequest {

    @NotNull
    private String name;

    @NotNull
    private String accountNumber;

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
