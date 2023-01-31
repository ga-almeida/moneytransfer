package br.com.caseitau.moneytransfer.client.mapper;

import br.com.caseitau.moneytransfer.client.controller.createTransfer.CreateTransferRequest;
import br.com.caseitau.moneytransfer.client.controller.createTransfer.CreateTransferResponse;
import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.domain.entity.TransferEntity;

public final class TransferMapper {

    public static TransferEntity createTransferRequestFromTransferEntity(CreateTransferRequest createTransferRequest) {
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

    public static CreateTransferResponse transferEntityFromCreateTransferResponse(TransferEntity transferEntity) {
        return CreateTransferResponse.builder()
                .id(transferEntity.getId())
                .originClientId(transferEntity.getOriginClient().getId())
                .fromClientId(transferEntity.getFromClient().getId())
                .balance(transferEntity.getBalance())
                .createdAt(transferEntity.getCreatedAt())
                .build();
    }
}
