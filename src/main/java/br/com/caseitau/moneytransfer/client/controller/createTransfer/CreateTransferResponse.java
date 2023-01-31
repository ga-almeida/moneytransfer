package br.com.caseitau.moneytransfer.client.controller.createTransfer;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public class CreateTransferResponse {
    private UUID id;
    private UUID originClientId;
    private UUID fromClientId;
    private BigDecimal balance;
    private ZonedDateTime createdAt;

    private CreateTransferResponse(UUID id, UUID originClientId, UUID fromClientId, BigDecimal balance, ZonedDateTime createdAt) {
        this.id = id;
        this.originClientId = originClientId;
        this.fromClientId = fromClientId;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getOriginClientId() {
        return originClientId;
    }

    public UUID getFromClientId() {
        return fromClientId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private UUID originClientId;
        private UUID fromClientId;
        private BigDecimal balance;
        private ZonedDateTime createdAt;

        private Builder() {
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder originClientId(UUID originClientId) {
            this.originClientId = originClientId;
            return this;
        }

        public Builder fromClientId(UUID fromClientId) {
            this.fromClientId = fromClientId;
            return this;
        }

        public Builder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder createdAt(ZonedDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CreateTransferResponse build() {
            return new CreateTransferResponse(id, originClientId, fromClientId, balance, createdAt);
        }
    }
}
