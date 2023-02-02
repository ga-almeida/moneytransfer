package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.domain.entity.TransferEntity;
import br.com.caseitau.moneytransfer.client.domain.model.StatusEnum;
import br.com.caseitau.moneytransfer.client.dto.CreateTransferRequest;
import br.com.caseitau.moneytransfer.client.mapper.TransferMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TransferService implements ITransferService {

    private final TransferRepository transferRepository;

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public TransferEntity save(
            CreateTransferRequest createTransferRequest,
            ClientEntity originClient,
            ClientEntity fromClient,
            StatusEnum status
    ) {
        var transferEntity = TransferMapper.createTransferRequestFromTransferEntity(
                createTransferRequest.getValue(),
                originClient,
                fromClient,
                status);

        return transferRepository.saveAndFlush(transferEntity);
    }

    @Override
    public List<TransferEntity> findByClientId(UUID originClientId) {
        return transferRepository.findByOriginClientIdOrderByCreatedAtDesc(originClientId);
    }
}
