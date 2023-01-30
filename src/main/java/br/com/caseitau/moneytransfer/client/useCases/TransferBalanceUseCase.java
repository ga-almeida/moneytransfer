package br.com.caseitau.moneytransfer.client.useCases;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.domain.entity.TransferEntity;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.domain.repository.TransferRepository;
import br.com.caseitau.moneytransfer.client.dto.ClientDTO;
import br.com.caseitau.moneytransfer.client.dto.TransferDTO;
import br.com.caseitau.moneytransfer.client.exception.AccountNumberAlreadyExistsExcepetion;
import br.com.caseitau.moneytransfer.client.mapper.TransferMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferBalanceUseCase {

    private TransferRepository transferRepository;

    public TransferBalanceUseCase(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Transactional
    public TransferEntity execute(TransferDTO transferDTO) {
        return transferRepository.save(transferDTO);
    }
}
