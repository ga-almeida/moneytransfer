package br.com.caseitau.moneytransfer.client.useCases;

import br.com.caseitau.moneytransfer.client.domain.repository.IClientService;
import br.com.caseitau.moneytransfer.client.domain.repository.ITransferService;
import br.com.caseitau.moneytransfer.client.dto.ListTransfersByClientResponse;
import br.com.caseitau.moneytransfer.client.exception.ClientNotFoundException;
import br.com.caseitau.moneytransfer.client.mapper.TransferMapper;
import org.springframework.stereotype.Service;

@Service
public class ListTransfersByClientUseCase {

    private final ITransferService transferService;
    private final IClientService clientService;

    public ListTransfersByClientUseCase(ITransferService transferService, IClientService clientService) {
        this.transferService = transferService;
        this.clientService = clientService;
    }

    public ListTransfersByClientResponse execute(String accountNumber) {
        var clientByAccountNumber = clientService.findOneByAccountNumber(accountNumber)
                .orElseThrow(ClientNotFoundException::new);

        var transfersByClientId = transferService.findByClientId(clientByAccountNumber.getId());

        return TransferMapper.transfersEntityFromListTransfersByClientResponse(
                transfersByClientId, clientByAccountNumber.getAccountNumber(), clientByAccountNumber.getName());
    }
}
