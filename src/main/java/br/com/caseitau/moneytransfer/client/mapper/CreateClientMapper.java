package br.com.caseitau.moneytransfer.client.mapper;

import br.com.caseitau.moneytransfer.client.controller.createClient.CreateClientRequest;
import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.dto.CreateClientDTO;

public final class CreateClientMapper {

    public static ClientEntity dtoFromEntity(CreateClientDTO createClientDTO) {
        return ClientEntity.builder()
                .name(createClientDTO.getName())
                .accountNumber(createClientDTO.getAccountNumber())
                .accountBalance(createClientDTO.getAccountBalance())
                .build();
    }

    public static CreateClientDTO requestFromDto(CreateClientRequest createClientRequest) {
        return CreateClientDTO.builder()
                .name(createClientRequest.getName())
                .accountNumber(createClientRequest.getAccountNumber())
                .accountBalance(createClientRequest.getAccountBalance())
                .build();
    }
}
