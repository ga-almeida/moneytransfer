package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.domain.entity.TransferEntity;
import br.com.caseitau.moneytransfer.client.dto.TransferDTO;

public interface TransferRepository {
    TransferEntity save(TransferDTO transferDTO);
}
