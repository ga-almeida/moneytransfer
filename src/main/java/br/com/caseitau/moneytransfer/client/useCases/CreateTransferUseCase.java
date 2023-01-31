package br.com.caseitau.moneytransfer.client.useCases;

import br.com.caseitau.moneytransfer.client.controller.createTransfer.CreateTransferRequest;
import br.com.caseitau.moneytransfer.client.controller.createTransfer.CreateTransferResponse;
import br.com.caseitau.moneytransfer.client.domain.entity.TransferEntity;
import br.com.caseitau.moneytransfer.client.domain.repository.TransferRepository;
import br.com.caseitau.moneytransfer.client.mapper.TransferMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateTransferUseCase {

    private TransferRepository transferRepository;

    public CreateTransferUseCase(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Transactional
    public CreateTransferResponse execute(CreateTransferRequest createTransferRequest) {
        TransferEntity transferEntity = transferRepository.save(createTransferRequest).get();
        return TransferMapper.transferEntityFromCreateTransferResponse(transferEntity);
    }
}
