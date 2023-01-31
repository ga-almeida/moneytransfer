package br.com.caseitau.moneytransfer.client.useCases;

import br.com.caseitau.moneytransfer.client.controller.findOneClient.FindOneClientResponse;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.exception.ClientNotFoundException;
import br.com.caseitau.moneytransfer.client.mapper.ClientMapper;
import org.springframework.stereotype.Service;

@Service
public class FindOneClientUseCase {

    private final ClientRepository clientRepository;

    public FindOneClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public FindOneClientResponse execute(String accountNumber) {
        var clientEntity = clientRepository.findOne(accountNumber).orElseThrow(ClientNotFoundException::new);
        return ClientMapper.clientEntityFromFindOneClientResponse(clientEntity);
    }
}
