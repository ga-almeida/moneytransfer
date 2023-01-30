package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.dto.CreateClientDTO;

import java.util.List;

public interface ClientRepository {
    ClientEntity save(CreateClientDTO createClientDTO);

    Boolean findClientByAccountNumber(String accountNumber);
    List<ClientEntity> findAll();
}
