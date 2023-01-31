package br.com.caseitau.moneytransfer.client.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public class CreateClientResponse {
    private UUID id;
    private String name;
    private String accountNumber;
    private BigDecimal accountBalance;
    private ZonedDateTime createdAt;

    public static Builder builder() {
        return new Builder();
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

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    private CreateClientResponse(UUID id, String name, String accountNumber, BigDecimal accountBalance, ZonedDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.createdAt = createdAt;
    }
    public static final class Builder {
        private UUID id;
        private String name;
        private String accountNumber;
        private BigDecimal accountBalance;
        private ZonedDateTime createdAt;

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

        public Builder createdAt(ZonedDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CreateClientResponse build() {
            return new CreateClientResponse(id, name, accountNumber, accountBalance, createdAt);
        }
    }
}
