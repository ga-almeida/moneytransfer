package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.dto.CreateClientRequest;
import br.com.caseitau.moneytransfer.client.mapper.ClientMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientEntity save(CreateClientRequest createClientRequest) {
        var clientEntity = ClientMapper.createClientRequestFromClientEntity(createClientRequest);
        return clientRepository.saveAndFlush(clientEntity);
    }

    @Override
    public Boolean findClientByAccountNumber(String accountNumber) {
        return clientRepository.findByAccountNumber(accountNumber).isPresent();
    }

    @Override
    public List<ClientEntity> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<ClientEntity> findOneByAccountNumber(String accountNumber) {
        return clientRepository.findByAccountNumber(accountNumber);
    }
}
