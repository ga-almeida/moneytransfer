package br.com.caseitau.moneytransfer.client.mapper;

import br.com.caseitau.moneytransfer.client.controller.createClient.CreateClientRequest;
import br.com.caseitau.moneytransfer.client.controller.createClient.CreateClientResponse;
import br.com.caseitau.moneytransfer.client.controller.findOneClient.FindOneClientResponse;
import br.com.caseitau.moneytransfer.client.controller.listClients.ListClientsResponse;
import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;

import java.util.List;

public final class ClientMapper {

    public static ClientEntity createClientRequestFromClientEntity(CreateClientRequest createClientRequest) {
        return ClientEntity.builder()
                .name(createClientRequest.getName())
                .accountNumber(createClientRequest.getAccountNumber())
                .accountBalance(createClientRequest.getAccountBalance())
                .build();
    }

    public static CreateClientResponse clientEntityFromCreateClientResponse(ClientEntity clientEntity) {
        return CreateClientResponse.builder()
                .id(clientEntity.getId())
                .name(clientEntity.getName())
                .accountNumber(clientEntity.getAccountNumber())
                .accountBalance(clientEntity.getAccountBalance())
                .createdAt(clientEntity.getCreatedAt())
                .build();
    }

    public static ListClientsResponse clientEntityListFromListClientsResponse(List<ClientEntity> clientEntityList) {
        return ListClientsResponse.builder()
                .clients(clientEntityList)
                .build();
    }

    public static FindOneClientResponse clientEntityFromFindOneClientResponse(ClientEntity clientEntity) {
        return FindOneClientResponse.builder()
                .id(clientEntity.getId())
                .name(clientEntity.getName())
                .accountNumber(clientEntity.getAccountNumber())
                .accountBalance(clientEntity.getAccountBalance())
                .createdAt(clientEntity.getCreatedAt())
                .updatedAt(clientEntity.getUpdatedAt())
                .build();
    }
}
