package br.com.caseitau.moneytransfer.client.mapper;

import br.com.caseitau.moneytransfer.client.controller.createClient.CreateClientRequest;
import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.dto.ClientDTO;

public final class CreateClientMapper {

    public static ClientEntity dtoFromEntity(ClientDTO createClientDTO) {
        return ClientEntity.builder()
                .name(createClientDTO.getName())
                .accountNumber(createClientDTO.getAccountNumber())
                .accountBalance(createClientDTO.getAccountBalance())
                .build();
    }

    public static ClientDTO requestFromDto(CreateClientRequest createClientRequest) {
        return ClientDTO.builder()
                .name(createClientRequest.getName())
                .accountNumber(createClientRequest.getAccountNumber())
                .accountBalance(createClientRequest.getAccountBalance())
                .build();
    }
}
