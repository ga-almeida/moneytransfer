package br.com.caseitau.moneytransfer.client.useCases;

import br.com.caseitau.moneytransfer.client.controller.createClient.CreateClientRequest;
import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.exception.AccountNumberAlreadyExistsExcepetion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateClientUseCase {

    private final ClientRepository clientRepository;

    public CreateClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientEntity execute(CreateClientRequest createClientRequest) {
        var clientExists = clientRepository.findClientByAccountNumber(createClientRequest.getAccountNumber());
        if (clientExists) {
            throw new AccountNumberAlreadyExistsExcepetion();
        }

        return clientRepository.save(createClientRequest);
    }
}
