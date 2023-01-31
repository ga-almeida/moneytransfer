package br.com.caseitau.moneytransfer.client.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateClientRequest {

    @NotNull
    private String name;

    @NotNull
    private String accountNumber;

    @NotNull
    private BigDecimal accountBalance;

    private CreateClientRequest(String name, String accountNumber, BigDecimal accountBalance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
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
        private @NotNull String name;
        private @NotNull String accountNumber;
        private @NotNull BigDecimal accountBalance;

        private Builder() {
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

        public CreateClientRequest build() {
            return new CreateClientRequest(name, accountNumber, accountBalance);
        }
    }
}
