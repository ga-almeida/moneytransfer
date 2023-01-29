package br.com.caseitau.moneytransfer.client.controller;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateClientRequest {

    @NotNull
    private String name;

    @NotNull
    private String accountNumber;

    @NotNull
    private BigDecimal accountBalance;

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }
}
