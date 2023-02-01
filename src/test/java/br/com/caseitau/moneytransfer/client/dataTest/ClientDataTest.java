package br.com.caseitau.moneytransfer.client.dataTest;

import br.com.caseitau.moneytransfer.client.dto.CreateClientRequest;
import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.mapper.ClientMapper;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.com.caseitau.moneytransfer.client.dataTest.ClientConstants.*;

public final class ClientDataTest {
    public static CreateClientRequest basicCreateClientRequestJohnDoe() {
        return CreateClientRequest.builder()
                .name(NAME_JOHN_DOE)
                .accountNumber(ACCOUNT_NUMBER_JOHN_DOE)
                .accountBalance(ACCOUNT_BALANCE_JOHN_DOE)
                .build();
    }

    public static CreateClientRequest basicCreateClientRequestJaneDoe() {
        return CreateClientRequest.builder()
                .name(NAME_JANE_DOE)
                .accountNumber(ACCOUNT_NUMBER_JANE_DOE)
                .accountBalance(ACCOUNT_BALANCE_JANE_DOE)
                .build();
    }

    public static CreateClientRequest basicCreateClientRequestCustom(String name, String accountNumber, BigDecimal accountBalance) {
        return CreateClientRequest.builder()
                .name(name)
                .accountNumber(accountNumber)
                .accountBalance(accountBalance)
                .build();
    }

    public static List<ClientEntity> basicCreateFiveClients() {
        List<ClientEntity> clients = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            clients.add(ClientMapper.createClientRequestFromClientEntity(basicCreateClientRequestJohnDoe()));
        }

        return clients;
    }

    public static List<ClientEntity> basicCreateFiveCustomClients() {
        List<ClientEntity> clients = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            var clientRequest = basicCreateClientRequestCustom(
                    NAME_JOHN_DOE + i,
                    ACCOUNT_NUMBER_JOHN_DOE + i,
                    ACCOUNT_BALANCE_JOHN_DOE.add(BigDecimal.valueOf(i))
            );
            clients.add(ClientMapper.createClientRequestFromClientEntity(clientRequest));
        }

        return clients;
    }

    public static ClientEntity basicCreateClientEntityJohnDoe() {
        return ClientEntity.builder()
                .id(UUID.randomUUID())
                .name(NAME_JOHN_DOE)
                .accountNumber(ACCOUNT_NUMBER_JOHN_DOE)
                .accountBalance(ACCOUNT_BALANCE_JOHN_DOE)
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();
    }

    public static ClientEntity basicCreateClientEntityJaneDoe() {
        return ClientEntity.builder()
                .id(UUID.randomUUID())
                .name(NAME_JANE_DOE)
                .accountNumber(ACCOUNT_NUMBER_JANE_DOE)
                .accountBalance(ACCOUNT_BALANCE_JANE_DOE)
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();
    }

    public static Optional<ClientEntity> basicFindOneClientEntityJohnDoe() {
        return Optional.of(ClientEntity.builder()
                .id(UUID.randomUUID())
                .name(NAME_JOHN_DOE)
                .accountNumber(ACCOUNT_NUMBER_JOHN_DOE)
                .accountBalance(ACCOUNT_BALANCE_JOHN_DOE)
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build());
    }

    public static Optional<ClientEntity> basicFindOneClientEntityJaneDoe() {
        return Optional.of(ClientEntity.builder()
                .id(UUID.randomUUID())
                .name(NAME_JANE_DOE)
                .accountNumber(ACCOUNT_NUMBER_JANE_DOE)
                .accountBalance(ACCOUNT_BALANCE_JANE_DOE)
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build());
    }
}
