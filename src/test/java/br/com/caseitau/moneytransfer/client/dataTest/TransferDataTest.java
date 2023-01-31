package br.com.caseitau.moneytransfer.client.dataTest;

import br.com.caseitau.moneytransfer.client.controller.createTransfer.CreateTransferRequest;
import br.com.caseitau.moneytransfer.client.controller.createTransfer.CreateTransferResponse;
import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.domain.entity.TransferEntity;

import javax.swing.text.html.Option;
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
                        .build())
                .originClient(ClientEntity.builder()
                        .id(ORIGIN_CLIENT_ID)
                        .build())
                .balance(BALANCE)
                .createdAt(ZonedDateTime.now())
                .build());
    }

    public static CreateTransferRequest basicCreateTransferRequest() {
        return CreateTransferRequest.builder()
                .fromClientId(FROM_CLIENT_ID)
                .originClientId(ORIGIN_CLIENT_ID)
                .balance(BALANCE)
                .build();
    }
}
