package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.domain.model.StatusEnum;
import br.com.caseitau.moneytransfer.client.dto.CreateTransferRequest;
import br.com.caseitau.moneytransfer.client.domain.entity.TransferEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITransferService {
    TransferEntity save(CreateTransferRequest createTransferRequest,
                                  ClientEntity originClient,
                                  ClientEntity fromClient,
                                  StatusEnum status);

    List<TransferEntity> findByClientId(UUID clientId);
}
