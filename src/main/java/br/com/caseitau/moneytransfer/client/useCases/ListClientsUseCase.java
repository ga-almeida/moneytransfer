package br.com.caseitau.moneytransfer.client.useCases;

import br.com.caseitau.moneytransfer.client.dto.ListClientsResponse;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.mapper.ClientMapper;
import org.springframework.stereotype.Service;

@Service
public class ListClientsUseCase {

    private final ClientRepository clientRepository;

    public ListClientsUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ListClientsResponse execute() {
        return ClientMapper.clientEntityListFromListClientsResponse(clientRepository.findAll());
    }
}
