package br.com.caseitau.moneytransfer.client.domain.entity;

import br.com.caseitau.moneytransfer.client.domain.model.StatusEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "transfers")
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
    private BigDecimal value;

    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

    @Column
    private OffsetDateTime createdAt;

    @Column
    private OffsetDateTime updatedAt;

    public TransferEntity() {
    }

    private TransferEntity(UUID id, ClientEntity originClient, ClientEntity fromClient, BigDecimal value, StatusEnum status, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.originClient = originClient;
        this.fromClient = fromClient;
        this.value = value;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    @PrePersist
    protected void prePersist() {
        createdAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = OffsetDateTime.now();
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

    public BigDecimal getValue() {
        return value;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static final class Builder {
        private UUID id;
        private ClientEntity originClient;
        private ClientEntity fromClient;
        private BigDecimal value;
        private StatusEnum status;
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;

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

        public Builder updatedAt(OffsetDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public TransferEntity build() {
            return new TransferEntity(id, originClient, fromClient, value, status, createdAt, updatedAt);
        }
    }
}
