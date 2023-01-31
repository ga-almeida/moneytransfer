package br.com.caseitau.moneytransfer.client.useCases;

import br.com.caseitau.moneytransfer.client.controller.createTransfer.CreateTransferRequest;
import br.com.caseitau.moneytransfer.client.controller.createTransfer.CreateTransferResponse;
import br.com.caseitau.moneytransfer.client.domain.repository.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferBalanceUseCase {

    private TransferRepository transferRepository;

    public TransferBalanceUseCase(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Transactional
    public CreateTransferResponse execute(CreateTransferRequest createTransferRequest) {
        return transferRepository.save(createTransferRequest).get();
    }
}
