package br.com.caseitau.moneytransfer.client.controller.createTransfer;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateTransferRequest {
    private UUID originClientId;
    private UUID fromClientId;
    private BigDecimal balance;

    public CreateTransferRequest(UUID originClientId, UUID fromClientId, BigDecimal balance) {
        this.originClientId = originClientId;
        this.fromClientId = fromClientId;
        this.balance = balance;
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

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID originClientId;
        private UUID fromClientId;
        private BigDecimal balance;

        private Builder() {
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

        public CreateTransferRequest build() {
            return new CreateTransferRequest(originClientId, fromClientId, balance);
        }
    }
}
