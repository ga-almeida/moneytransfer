package br.com.caseitau.moneytransfer.client.dataTest;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.domain.entity.TransferEntity;
import br.com.caseitau.moneytransfer.client.domain.model.StatusEnum;
import br.com.caseitau.moneytransfer.client.dto.CreateTransferRequest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.com.caseitau.moneytransfer.client.dataTest.TransferConstants.*;

public final class TransferDataTest {
    public static TransferEntity basicCreateTransferEntity() {
        return TransferEntity.builder()
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
                .createdAt(OffsetDateTime.now())
                .build();
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

    public static List<TransferEntity> listTransfersbyClient() {
        List<TransferEntity> transfer = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            transfer.add(basicCreateTransferEntity());
        }

        return transfer;
    }
}
