package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.dto.CreateClientDTO;

public interface ClientRepository {
    CreateClientDTO save(CreateClientDTO createClientDTO);
}
