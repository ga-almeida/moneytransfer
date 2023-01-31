package br.com.caseitau.moneytransfer.client.mapper;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.domain.entity.TransferEntity;
import br.com.caseitau.moneytransfer.client.domain.model.StatusEnum;
import br.com.caseitau.moneytransfer.client.dto.CreateTransferResponse;
import br.com.caseitau.moneytransfer.client.dto.ListTransfersByClientResponse;
import br.com.caseitau.moneytransfer.client.dto.TransferByClientDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public final class TransferMapper {

    public static TransferEntity createTransferRequestFromTransferEntity(
            BigDecimal value, ClientEntity originClient, ClientEntity fromClient
    ) {
        return TransferEntity.builder()
                .originClient(originClient)
                .fromClient(fromClient)
                .value(value)
                .status(StatusEnum.SUCCESS)
                .build();
    }

    public static CreateTransferResponse transferEntityFromCreateTransferResponse(TransferEntity transferEntity) {
        return CreateTransferResponse.builder()
                .id(transferEntity.getId())
                .originClientAccountNumber(transferEntity.getOriginClient().getAccountNumber())
                .fromClientAccountNumber(transferEntity.getFromClient().getAccountNumber())
                .value(transferEntity.getValue())
                .createdAt(transferEntity.getCreatedAt())
                .status(transferEntity.getStatus())
                .build();
    }

    public static ListTransfersByClientResponse transfersEntityFromListTransfersByClientResponse(
            List<TransferEntity> transfers, String accountNumber, String name
    ) {
        var transfersByClient = transfers.stream()
                .map(t -> TransferByClientDTO.builder()
                        .fromClientAccountNumber(t.getFromClient().getAccountNumber())
                        .value(t.getValue())
                        .status(t.getStatus())
                        .createdAt(t.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return ListTransfersByClientResponse.builder()
                .name(name)
                .accountNumber(accountNumber)
                .transfers(transfersByClient)
                .build();
    }
}
