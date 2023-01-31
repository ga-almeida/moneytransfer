package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.dto.CreateClientRequest;
import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    ClientEntity save(CreateClientRequest createClientRequest);
    Boolean findClientByAccountNumber(String accountNumber);
    List<ClientEntity> findAll();
    Optional<ClientEntity> findOne(String accountNumber);
}
