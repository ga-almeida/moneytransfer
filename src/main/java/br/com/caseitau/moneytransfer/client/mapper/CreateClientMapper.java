package br.com.caseitau.moneytransfer.client.mapper;

import br.com.caseitau.moneytransfer.client.controller.createClient.CreateClientResponse;
import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.dto.CreateClientDTO;

public final class CreateClientMapper {

    public static CreateClientResponse dtoFromResponse(CreateClientDTO createClientDTO) {
        return CreateClientResponse.builder()
                .id(createClientDTO.getId())
                .name(createClientDTO.getName())
                .accountNumber(createClientDTO.getAccountNumber())
                .accountBalance(createClientDTO.getAccountBalance())
                .createdAt(createClientDTO.getCreatedAt())
                .build();
    }

    public static ClientEntity dtoFromEntity(CreateClientDTO createClientDTO) {
        return ClientEntity.builder()
                .name(createClientDTO.getName())
                .accountNumber(createClientDTO.getAccountNumber())
                .accountBalance(createClientDTO.getAccountBalance())
                .build();
    }

    public static CreateClientDTO entityFromDto(ClientEntity clientEntity) {
        return CreateClientDTO.builder()
                .id(clientEntity.getId())
                .name(clientEntity.getName())
                .accountNumber(clientEntity.getAccountNumber())
                .accountBalance(clientEntity.getAccountBalance())
                .createdAt(clientEntity.getCreatedAt())
                .build();
    }
}
