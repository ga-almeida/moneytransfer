package br.com.caseitau.moneytransfer.client.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "clients")
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "origin_client_id")
    private ClientEntity originClient;

    @ManyToOne
    @JoinColumn(name = "from_client_id")
    private ClientEntity fromClient;

    @Column
    private BigDecimal balance;

    @Column
    private ZonedDateTime createdAt;

    @Column
    private ZonedDateTime updatedAt;

    private TransferEntity(UUID id, ClientEntity originClient, ClientEntity fromClient, BigDecimal balance, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.originClient = originClient;
        this.fromClient = fromClient;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void prePersist() {
        createdAt = ZonedDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = ZonedDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public ClientEntity getOriginClient() {
        return originClient;
    }

    public ClientEntity getFromClient() {
        return fromClient;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private ClientEntity originClient;
        private ClientEntity fromClient;
        private BigDecimal balance;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

        private Builder() {
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder originClient(ClientEntity originClient) {
            this.originClient = originClient;
            return this;
        }

        public Builder fromClient(ClientEntity fromClient) {
            this.fromClient = fromClient;
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

        public Builder updatedAt(ZonedDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public TransferEntity build() {
            return new TransferEntity(id, originClient, fromClient, balance, createdAt, updatedAt);
        }
    }
}
