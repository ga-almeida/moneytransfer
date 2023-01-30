package br.com.caseitau.moneytransfer.client.mapper;

import br.com.caseitau.moneytransfer.client.controller.createClient.CreateClientRequest;
import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.domain.entity.TransferEntity;
import br.com.caseitau.moneytransfer.client.dto.ClientDTO;
import br.com.caseitau.moneytransfer.client.dto.TransferDTO;

public final class TransferMapper {

    public static TransferEntity dtoFromEntity(TransferDTO transferDTO) {
        return TransferEntity.builder()
                .id(transferDTO.getId())
                .originClient(transferDTO.getOriginClient())
                .fromClient(transferDTO.getFromClient())
                .balance(transferDTO.getBalance())
                .createdAt(transferDTO.getCreatedAt())
                .updatedAt(transferDTO.getUpdatedAt())
                .build();
    }
}
