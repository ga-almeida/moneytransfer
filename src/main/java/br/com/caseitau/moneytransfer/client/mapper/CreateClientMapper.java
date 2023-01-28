package br.com.caseitau.moneytransfer.client.mapper;

import br.com.caseitau.moneytransfer.client.controller.CreateClientResponse;
import br.com.caseitau.moneytransfer.client.domain.ClientEntity;
import br.com.caseitau.moneytransfer.client.useCases.createClientUseCase.CreateClientDTO;

public final class CreateClientMapper {

    public static CreateClientResponse EntityFromResponse(ClientEntity clientEntity) {
        return CreateClientResponse.builder()
                .id(clientEntity.getId())
                .name(clientEntity.getName())
                .accountNumber(clientEntity.getAccountNumber())
                .accountBalance(clientEntity.getAccountBalance())
                .build();
    }

    public static ClientEntity DtoFromEntity(CreateClientDTO createClientDTO) {
        return ClientEntity.builder()
                .name(createClientDTO.getName())
                .accountNumber(createClientDTO.getAccountNumber())
                .accountBalance(createClientDTO.getAccountBalance())
                .build();
    }
}
