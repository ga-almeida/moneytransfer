package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.dto.ClientDTO;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientRepositoryInMemory implements ClientRepository {

    private List<ClientEntity> clientEntities = new ArrayList<>();

    @Override
    public ClientEntity save(ClientDTO createClientDTO) {
        var clientEntity = ClientEntity.builder()
                .id(UUID.randomUUID())
                .name(createClientDTO.getName())
                .accountNumber(createClientDTO.getAccountNumber())
                .accountBalance(createClientDTO.getAccountBalance())
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();

        clientEntities.add(clientEntity);

        return clientEntity;
    }

    @Override
    public Boolean findClientByAccountNumber(String accountNumber) {
        return clientEntities.stream().map(ClientEntity::getAccountNumber).anyMatch(accountNumber::equals);
    }

    @Override
    public List<ClientEntity> findAll() {
        return clientEntities;
    }

    @Override
    public Optional<ClientEntity> findOne(String accountNumber) {
        return clientEntities.stream().filter(client -> client.getAccountNumber().equals(accountNumber)).findFirst();
    }
}
