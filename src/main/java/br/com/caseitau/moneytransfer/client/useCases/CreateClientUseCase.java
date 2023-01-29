package br.com.caseitau.moneytransfer.client.useCases;

import br.com.caseitau.moneytransfer.client.controller.CreateClientResponse;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.dto.CreateClientDTO;
import br.com.caseitau.moneytransfer.client.mapper.CreateClientMapper;
import org.springframework.stereotype.Service;

@Service
public class CreateClientUseCase {

    private final ClientRepository clientRepository;

    public CreateClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public CreateClientResponse execute(CreateClientDTO createClientDTO) {
        var clientPersist = clientRepository.save(createClientDTO);

        return CreateClientMapper.dtoFromResponse(clientPersist);
    }
}
