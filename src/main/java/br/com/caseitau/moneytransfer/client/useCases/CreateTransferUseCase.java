package br.com.caseitau.moneytransfer.client.useCases;

import br.com.caseitau.moneytransfer.client.domain.model.StatusEnum;
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

    public CreateTransferResponse execute(CreateTransferRequest createTransferRequest) {
        var originClient = clientService.findOneByAccountNumber(createTransferRequest.getOriginClientAccountNumber())
                .orElseThrow(() -> {
                    transferService.save(createTransferRequest, null, null, StatusEnum.REJECTED_CLIENT_NOT_EXISTS);
                    throw new ClientNotFoundException();
        });

        var fromClient = clientService.findOneByAccountNumber(createTransferRequest.getFromClientAccountNumber())
                .orElseThrow(() -> {
                    transferService.save(createTransferRequest, null, null, StatusEnum.REJECTED_CLIENT_NOT_EXISTS);
                    throw new ClientNotFoundException();
        });

        if (originClient.getAccountBalance().compareTo(createTransferRequest.getValue()) < COMPARE_BALANCE_WITH_VALUE) {
            transferService.save(createTransferRequest, originClient, fromClient, StatusEnum.REJECTED_ORIGIN_VALUE_HIGHER);
            throw new ValueIsHigherThanAccountExcepetion();
        }

        var transferEntity = transferService.save(createTransferRequest, originClient, fromClient, StatusEnum.SUCCESS);
        return TransferMapper.transferEntityFromCreateTransferResponse(transferEntity);
    }
}
