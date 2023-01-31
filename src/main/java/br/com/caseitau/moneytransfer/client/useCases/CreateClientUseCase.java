package br.com.caseitau.moneytransfer.client.useCases;

import br.com.caseitau.moneytransfer.client.dto.CreateClientRequest;
import br.com.caseitau.moneytransfer.client.dto.CreateClientResponse;
import br.com.caseitau.moneytransfer.client.domain.repository.IClientService;
import br.com.caseitau.moneytransfer.client.exception.AccountNumberAlreadyExistsExcepetion;
import br.com.caseitau.moneytransfer.client.mapper.ClientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateClientUseCase {

    private final IClientService clientService;

    public CreateClientUseCase(IClientService clientService) {
        this.clientService = clientService;
    }

    @Transactional
    public CreateClientResponse execute(CreateClientRequest createClientRequest) {
        var clientExists = clientService.findClientByAccountNumber(createClientRequest.getAccountNumber());
        if (clientExists) {
            throw new AccountNumberAlreadyExistsExcepetion();
        }
        var clientEntity = clientService.save(createClientRequest);
        return ClientMapper.clientEntityFromCreateClientResponse(clientEntity);
    }
}
