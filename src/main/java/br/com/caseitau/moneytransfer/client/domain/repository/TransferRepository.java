package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.controller.createClient.CreateClientResponse;
import br.com.caseitau.moneytransfer.client.controller.createTransfer.CreateTransferRequest;
import br.com.caseitau.moneytransfer.client.controller.createTransfer.CreateTransferResponse;
import br.com.caseitau.moneytransfer.client.domain.entity.TransferEntity;

import java.util.Optional;

public interface TransferRepository {
    Optional<TransferEntity> save(CreateTransferRequest createTransferRequest);
}
