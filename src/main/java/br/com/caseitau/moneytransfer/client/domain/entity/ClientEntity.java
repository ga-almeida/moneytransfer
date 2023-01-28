package br.com.caseitau.moneytransfer.client.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private final UUID id;

    @Column
    private final String name;

    @Column(unique = true)
    private final String accountNumber;

    @Column
    private final BigDecimal accountBalance;

    @Column
    private ZonedDateTime createdAt;

    @Column
    private ZonedDateTime updatedAt;

    private ClientEntity(UUID id, String name, String accountNumber, BigDecimal accountBalance, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    protected void prePersist() {
        createdAt = ZonedDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = ZonedDateTime.now();
    }

    public static final class Builder {
        private UUID id;
        private String name;
        private String accountNumber;
        private BigDecimal accountBalance;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

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

        public Builder updatedAt(ZonedDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public ClientEntity build() {
            return new ClientEntity(id, name, accountNumber, accountBalance, createdAt, updatedAt);
        }
    }
}
