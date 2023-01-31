package br.com.caseitau.moneytransfer.client.dto;

import br.com.caseitau.moneytransfer.client.domain.model.StatusEnum;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public class CreateTransferResponse {
    private UUID id;
    private String originClientAccountNumber;
    private String fromClientAccountNumber;
    private BigDecimal value;
    private StatusEnum status;
    private ZonedDateTime createdAt;

    private CreateTransferResponse(UUID id, String originClientAccountNumber, String fromClientAccountNumber, BigDecimal value, StatusEnum status, ZonedDateTime createdAt) {
        this.id = id;
        this.originClientAccountNumber = originClientAccountNumber;
        this.fromClientAccountNumber = fromClientAccountNumber;
        this.value = value;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getOriginClientAccountNumber() {
        return originClientAccountNumber;
    }

    public String getFromClientAccountNumber() {
        return fromClientAccountNumber;
    }

    public BigDecimal getValue() {
        return value;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private String originClientAccountNumber;
        private String fromClientAccountNumber;
        private BigDecimal value;
        private StatusEnum status;
        private ZonedDateTime createdAt;

        private Builder() {
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder originClientAccountNumber(String originClientAccountNumber) {
            this.originClientAccountNumber = originClientAccountNumber;
            return this;
        }

        public Builder fromClientAccountNumber(String fromClientAccountNumber) {
            this.fromClientAccountNumber = fromClientAccountNumber;
            return this;
        }

        public Builder value(BigDecimal value) {
            this.value = value;
            return this;
        }

        public Builder status(StatusEnum status) {
            this.status = status;
            return this;
        }

        public Builder createdAt(ZonedDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CreateTransferResponse build() {
            return new CreateTransferResponse(id, originClientAccountNumber, fromClientAccountNumber, value, status, createdAt);
        }
    }
}
