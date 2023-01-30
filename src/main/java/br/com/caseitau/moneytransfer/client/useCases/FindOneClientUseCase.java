package br.com.caseitau.moneytransfer.client.useCases;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.dto.ClientDTO;
import br.com.caseitau.moneytransfer.client.exception.ClientNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindOneClientUseCase {

    private final ClientRepository clientRepository;

    public FindOneClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientEntity execute(String accountNumber) {
        return clientRepository.findOne(accountNumber).orElseThrow(ClientNotFoundException::new);
    }
}
