package br.com.caseitau.moneytransfer.client.mapper;

import br.com.caseitau.moneytransfer.client.controller.createClient.CreateClientRequest;
import br.com.caseitau.moneytransfer.client.controller.createClient.CreateClientResponse;
import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;

public final class CreateClientMapper {

    public static ClientEntity requestFromEntity(CreateClientRequest createClientRequest) {
        return ClientEntity.builder()
                .name(createClientRequest.getName())
                .accountNumber(createClientRequest.getAccountNumber())
                .accountBalance(createClientRequest.getAccountBalance())
                .build();
    }

    public static CreateClientResponse entityFromResponse(ClientEntity clientEntity) {
        return CreateClientResponse.builder()
                .name(clientEntity.getName())
                .accountNumber(clientEntity.getAccountNumber())
                .accountBalance(clientEntity.getAccountBalance())
                .createdAt(clientEntity.getCreatedAt())
                .build();
    }
}
