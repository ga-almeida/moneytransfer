package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.domain.entity.TransferEntity;
import br.com.caseitau.moneytransfer.client.dto.ClientDTO;
import br.com.caseitau.moneytransfer.client.dto.TransferDTO;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TransferRepositoryInMemory implements TransferRepository {

    private List<TransferEntity> transferEntities = new ArrayList<>();

    @Override
    public TransferEntity save(TransferDTO transferDTO) {
        var transferEntity = TransferEntity.builder()
                .id(UUID.randomUUID())
                .originClient(transferDTO.getOriginClient())
                .fromClient(transferDTO.getFromClient())
                .balance(transferDTO.getBalance())
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();

        transferEntities.add(transferEntity);

        return transferEntity;
    }
}
