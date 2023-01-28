package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.useCases.createClientUseCase.CreateClientDTO;

public interface ClientRepository {
    ClientPersist save(CreateClientDTO createClientDTO);
}
