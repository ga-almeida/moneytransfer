package br.com.caseitau.moneytransfer.client.controller;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateClientResponse {
    private UUID id;
    private String name;
    private String accountNumber;
    private BigDecimal accountBalance;

    public CreateClientResponse(UUID id, String name, String accountNumber, BigDecimal accountBalance) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private String name;
        private String accountNumber;
        private BigDecimal accountBalance;

        private Builder() {
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder accountBalance(BigDecimal accountBalance) {
            this.accountBalance = accountBalance;
            return this;
        }

        public CreateClientResponse build() {
            return new CreateClientResponse(id, name, accountNumber, accountBalance);
        }
    }
}