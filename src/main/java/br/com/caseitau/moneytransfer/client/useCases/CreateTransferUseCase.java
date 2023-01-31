package br.com.caseitau.moneytransfer.client.useCases;

import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.domain.repository.TransferRepository;
import br.com.caseitau.moneytransfer.client.dto.CreateTransferRequest;
import br.com.caseitau.moneytransfer.client.dto.CreateTransferResponse;
import br.com.caseitau.moneytransfer.client.exception.ClientNotFoundException;
import br.com.caseitau.moneytransfer.client.exception.ValueIsHigherThanAccountExcepetion;
import br.com.caseitau.moneytransfer.client.mapper.TransferMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateTransferUseCase {

    private static final Integer COMPARE_BALANCE_WITH_VALUE = 0;

    private final TransferRepository transferRepository;
    private final ClientRepository clientRepository;

    public CreateTransferUseCase(TransferRepository transferRepository, ClientRepository clientRepository) {
        this.transferRepository = transferRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public CreateTransferResponse execute(CreateTransferRequest createTransferRequest) {
        var originClient = clientRepository.findOne(createTransferRequest.getOriginClientAccountNumber())
                .orElseThrow(ClientNotFoundException::new);

        if (originClient.getAccountBalance().compareTo(createTransferRequest.getValue()) < COMPARE_BALANCE_WITH_VALUE) {
            throw new ValueIsHigherThanAccountExcepetion();
        }

        var transferEntity = transferRepository.save(createTransferRequest).get();
        return TransferMapper.transferEntityFromCreateTransferResponse(transferEntity);
    }
}
