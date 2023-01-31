package br.com.caseitau.moneytransfer.client.useCases;

import br.com.caseitau.moneytransfer.client.domain.repository.IClientService;
import br.com.caseitau.moneytransfer.client.domain.repository.ITransferService;
import br.com.caseitau.moneytransfer.client.dto.CreateTransferRequest;
import br.com.caseitau.moneytransfer.client.dto.CreateTransferResponse;
import br.com.caseitau.moneytransfer.client.exception.ClientNotFoundException;
import br.com.caseitau.moneytransfer.client.exception.ValueIsHigherThanAccountExcepetion;
import br.com.caseitau.moneytransfer.client.mapper.TransferMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.*;

@Service
public class CreateTransferUseCase {

    private static final Integer COMPARE_BALANCE_WITH_VALUE = 0;

    private final ITransferService transferService;
    private final IClientService clientService;

    public CreateTransferUseCase(ITransferService transferService, IClientService clientService) {
        this.transferService = transferService;
        this.clientService = clientService;
    }

    @Transactional
    public CreateTransferResponse execute(CreateTransferRequest createTransferRequest) {
        var originClientOptional = clientService.findOneByAccountNumber(createTransferRequest.getOriginClientAccountNumber());
        var originClient = originClientOptional.isPresent() ? originClientOptional.get() : null;

        var fromClientOptional = clientService.findOneByAccountNumber(createTransferRequest.getFromClientAccountNumber());
        var fromClient = fromClientOptional.isPresent() ? fromClientOptional.get() : null;

        if (isNull(originClient) || isNull(fromClient)) {
            transferService.save(createTransferRequest, originClient, fromClient);
            throw new ClientNotFoundException();
        }

        if (originClient.getAccountBalance().compareTo(createTransferRequest.getValue()) < COMPARE_BALANCE_WITH_VALUE) {
            transferService.save(createTransferRequest, originClient, fromClient);
            throw new ValueIsHigherThanAccountExcepetion();
        }

        var transferEntity = transferService.save(createTransferRequest, originClient, fromClient).get();
        return TransferMapper.transferEntityFromCreateTransferResponse(transferEntity);
    }
}
