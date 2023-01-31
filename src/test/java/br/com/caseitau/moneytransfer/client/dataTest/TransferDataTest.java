package br.com.caseitau.moneytransfer.client.dataTest;

import br.com.caseitau.moneytransfer.client.domain.model.StatusEnum;
import br.com.caseitau.moneytransfer.client.dto.CreateTransferRequest;
import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.domain.entity.TransferEntity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static br.com.caseitau.moneytransfer.client.dataTest.TransferConstants.*;

public final class TransferDataTest {
    public static Optional<TransferEntity> basicCreateTransferEntity() {
        return Optional.of(TransferEntity.builder()
                        .id(UUID.randomUUID())
                .fromClient(ClientEntity.builder()
                        .id(FROM_CLIENT_ID)
                        .accountNumber(FROM_CLIENT_ACCOUNT_NUMBER)
                        .build())
                .originClient(ClientEntity.builder()
                        .id(ORIGIN_CLIENT_ID)
                        .accountNumber(ORIGIN_CLIENT_ACCOUNT_NUMBER)
                        .build())
                .value(BALANCE)
                .status(StatusEnum.SUCCESS)
                .createdAt(ZonedDateTime.now())
                .build());
    }

    public static CreateTransferRequest basicCreateTransferRequest() {
        return CreateTransferRequest.builder()
                .fromClientAccountNumber(FROM_CLIENT_ACCOUNT_NUMBER)
                .originClientAccountNumber(ORIGIN_CLIENT_ACCOUNT_NUMBER)
                .value(BALANCE)
                .build();
    }

    public static CreateTransferRequest basicCreateTransferRequestWithValueHigherAccount() {
        return CreateTransferRequest.builder()
                .fromClientAccountNumber(FROM_CLIENT_ACCOUNT_NUMBER)
                .originClientAccountNumber(ORIGIN_CLIENT_ACCOUNT_NUMBER)
                .value(BigDecimal.valueOf(101))
                .build();
    }
}
