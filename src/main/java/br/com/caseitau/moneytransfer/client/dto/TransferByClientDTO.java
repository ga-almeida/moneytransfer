package br.com.caseitau.moneytransfer.client.dto;

import br.com.caseitau.moneytransfer.client.domain.model.StatusEnum;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class TransferByClientDTO {
    private final String fromClientAccountNumber;
    private final BigDecimal value;
    private final StatusEnum status;
    private final OffsetDateTime createdAt;

    private TransferByClientDTO(String fromClientAccountNumber, BigDecimal value, StatusEnum status, OffsetDateTime createdAt) {
        this.fromClientAccountNumber = fromClientAccountNumber;
        this.value = value;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Builder builder() {
        return new Builder();
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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public static final class Builder {
        private String fromClientAccountNumber;
        private BigDecimal value;
        private StatusEnum status;
        private OffsetDateTime createdAt;

        private Builder() {
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

        public Builder createdAt(OffsetDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public TransferByClientDTO build() {
            return new TransferByClientDTO(fromClientAccountNumber, value, status, createdAt);
        }
    }
}
