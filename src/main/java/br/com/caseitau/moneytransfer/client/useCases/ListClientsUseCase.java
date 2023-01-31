package br.com.caseitau.moneytransfer.client.useCases;

import br.com.caseitau.moneytransfer.client.dto.ListClientsResponse;
import br.com.caseitau.moneytransfer.client.domain.repository.IClientService;
import br.com.caseitau.moneytransfer.client.mapper.ClientMapper;
import org.springframework.stereotype.Service;

@Service
public class ListClientsUseCase {

    private final IClientService clientService;

    public ListClientsUseCase(IClientService clientService) {
        this.clientService = clientService;
    }

    public ListClientsResponse execute() {
        return ClientMapper.clientEntityListFromListClientsResponse(clientService.findAll());
    }
}
