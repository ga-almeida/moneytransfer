package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.domain.entity.TransferEntity;
import br.com.caseitau.moneytransfer.client.dto.CreateTransferRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TransferService implements ITransferService {
    @Override
    public Optional<TransferEntity> save(CreateTransferRequest createTransferRequest, ClientEntity originClient, ClientEntity fromClient) {
        return Optional.empty();
    }

    @Override
    public List<TransferEntity> findByClientId(UUID clientId) {
        return null;
    }
}
