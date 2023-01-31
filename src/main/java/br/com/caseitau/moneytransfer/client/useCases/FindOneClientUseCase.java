package br.com.caseitau.moneytransfer.client.useCases;

import br.com.caseitau.moneytransfer.client.dto.FindOneClientResponse;
import br.com.caseitau.moneytransfer.client.domain.repository.IClientService;
import br.com.caseitau.moneytransfer.client.exception.ClientNotFoundException;
import br.com.caseitau.moneytransfer.client.mapper.ClientMapper;
import org.springframework.stereotype.Service;

@Service
public class FindOneClientUseCase {

    private final IClientService clientService;

    public FindOneClientUseCase(IClientService clientService) {
        this.clientService = clientService;
    }

    public FindOneClientResponse execute(String accountNumber) {
        var clientEntity = clientService.findOneByAccountNumber(accountNumber).orElseThrow(ClientNotFoundException::new);
        return ClientMapper.clientEntityFromFindOneClientResponse(clientEntity);
    }
}
