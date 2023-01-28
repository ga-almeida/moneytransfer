package br.com.caseitau.moneytransfer.client.useCases.createClientUseCase;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateClientDTO {
    private String name;
    private String accountNumber;
    private BigDecimal accountBalance;

    private CreateClientDTO(String name, String accountNumber, BigDecimal accountBalance) {
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
        private String name;
        private String accountNumber;
        private BigDecimal accountBalance;

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

        public CreateClientDTO build() {
            return new CreateClientDTO(name, accountNumber, accountBalance);
        }
    }
}
