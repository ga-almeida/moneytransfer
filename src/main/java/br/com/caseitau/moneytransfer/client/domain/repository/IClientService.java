package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.dto.CreateClientRequest;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    ClientEntity save(CreateClientRequest createClientRequest);
    Boolean findClientByAccountNumber(String accountNumber);
    List<ClientEntity> findAll();
    Optional<ClientEntity> findOneByAccountNumber(String accountNumber);
}
