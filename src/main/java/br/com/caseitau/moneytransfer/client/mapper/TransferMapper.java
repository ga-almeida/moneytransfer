package br.com.caseitau.moneytransfer.client.mapper;

import br.com.caseitau.moneytransfer.client.controller.createTransfer.CreateTransferRequest;
import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.domain.entity.TransferEntity;

public final class TransferMapper {

    public static TransferEntity requestFromEntity(CreateTransferRequest createTransferRequest) {
        return TransferEntity.builder()
                .originClient(
                        ClientEntity.builder()
                                .id(createTransferRequest.getOriginClientId())
                                .build()
                ).fromClient(
                        ClientEntity.builder()
                                .id(createTransferRequest.getFromClientId())
                                .build()
                )
                .balance(createTransferRequest.getBalance())
                .build();
    }
}
