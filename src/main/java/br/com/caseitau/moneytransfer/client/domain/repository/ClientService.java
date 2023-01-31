package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.dto.CreateClientRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClientService implements IClientService {
    @Override
    public ClientEntity save(CreateClientRequest createClientRequest) {
        return null;
    }

    @Override
    public Boolean findClientByAccountNumber(String accountNumber) {
        return null;
    }

    @Override
    public List<ClientEntity> findAll() {
        return null;
    }

    @Override
    public Optional<ClientEntity> findOneByAccountNumber(String accountNumber) {
        return Optional.empty();
    }
}
