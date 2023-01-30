package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.dto.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    ClientEntity save(ClientDTO createClientDTO);
    Boolean findClientByAccountNumber(String accountNumber);
    List<ClientEntity> findAll();
    Optional<ClientEntity> findOne(String accountNumber);
}
