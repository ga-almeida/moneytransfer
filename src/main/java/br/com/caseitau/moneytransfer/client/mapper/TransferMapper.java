package br.com.caseitau.moneytransfer.client.mapper;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.domain.entity.TransferEntity;
import br.com.caseitau.moneytransfer.client.domain.model.StatusEnum;
import br.com.caseitau.moneytransfer.client.dto.CreateTransferResponse;

import java.math.BigDecimal;

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
}
