package br.com.caseitau.moneytransfer.client.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateTransferRequest {
    @NotNull
    private final String originClientAccountNumber;

    @NotNull
    private final String fromClientAccountNumber;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "1000.0")
    private final BigDecimal value;

    private CreateTransferRequest(String originClientAccountNumber, String fromClientAccountNumber, BigDecimal value) {
        this.originClientAccountNumber = originClientAccountNumber;
        this.fromClientAccountNumber = fromClientAccountNumber;
        this.value = value;
    }

    public static Builder builder() {
        return new Builder();
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

    public static final class Builder {
        private String originClientAccountNumber;
        private String fromClientAccountNumber;
        private BigDecimal value;

        private Builder() {
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

        public CreateTransferRequest build() {
            return new CreateTransferRequest(originClientAccountNumber, fromClientAccountNumber, value);
        }
    }
}
