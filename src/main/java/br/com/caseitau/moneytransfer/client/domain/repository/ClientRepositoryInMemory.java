package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.dto.CreateClientDTO;
import br.com.caseitau.moneytransfer.client.mapper.CreateClientMapper;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientRepositoryInMemory implements ClientRepository {

    private List<ClientEntity> clientEntities = new ArrayList<>();

    @Override
    public CreateClientDTO save(CreateClientDTO createClientDTO) {
        var clientEntity = ClientEntity.builder()
                .id(UUID.randomUUID())
                .name(createClientDTO.getName())
                .accountNumber(createClientDTO.getAccountNumber())
                .accountBalance(createClientDTO.getAccountBalance())
                .createdAt(ZonedDateTime.now())
                .build();

        clientEntities.add(clientEntity);

        return CreateClientMapper.entityFromDto(clientEntity);
    }

    @Override
    public Boolean findClientByAccountNumber(String accountNumber) {
        return clientEntities.stream().map(ClientEntity::getAccountNumber).anyMatch(accountNumber::equals);
    }
}
