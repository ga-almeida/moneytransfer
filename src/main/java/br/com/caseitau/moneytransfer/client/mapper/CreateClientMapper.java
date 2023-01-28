package br.com.caseitau.moneytransfer.client.mapper;

import br.com.caseitau.moneytransfer.client.controller.CreateClientResponse;
import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientPersist;
import br.com.caseitau.moneytransfer.client.useCases.createClientUseCase.CreateClientDTO;

public final class CreateClientMapper {

    public static CreateClientResponse persistFromResponse(ClientPersist clientPersist) {
        return CreateClientResponse.builder()
                .id(clientPersist.getId())
                .name(clientPersist.getName())
                .accountNumber(clientPersist.getAccountNumber())
                .accountBalance(clientPersist.getAccountBalance())
                .build();
    }

    public static ClientEntity dtoFromEntity(CreateClientDTO createClientDTO) {
        return ClientEntity.builder()
                .name(createClientDTO.getName())
                .accountNumber(createClientDTO.getAccountNumber())
                .accountBalance(createClientDTO.getAccountBalance())
                .build();
    }

    public static ClientPersist entityFromPersist(ClientEntity clientEntity) {
        return ClientPersist.builder()
                .id(clientEntity.getId())
                .name(clientEntity.getName())
                .accountNumber(clientEntity.getAccountNumber())
                .accountBalance(clientEntity.getAccountBalance())
                .createdAt(clientEntity.getCreatedAt())
                .updatedAt(clientEntity.getUpdatedAt())
                .build();
    }
}
