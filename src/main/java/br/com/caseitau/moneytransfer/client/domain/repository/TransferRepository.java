package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.controller.createClient.CreateClientResponse;
import br.com.caseitau.moneytransfer.client.controller.createTransfer.CreateTransferRequest;
import br.com.caseitau.moneytransfer.client.controller.createTransfer.CreateTransferResponse;

import java.util.Optional;

public interface TransferRepository {
    Optional<CreateTransferResponse> save(CreateTransferRequest createTransferRequest);
}
