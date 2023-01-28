package br.com.caseitau.moneytransfer.client.useCases.createClientUseCase;

import br.com.caseitau.moneytransfer.client.controller.CreateClientResponse;
import br.com.caseitau.moneytransfer.client.mapper.CreateClientMapper;

public class CreateClientUseCase {

    public CreateClientResponse execute(CreateClientDTO createClientDTO) {
        var clientEntity = CreateClientMapper.DtoFromEntity(createClientDTO);

        return CreateClientMapper.EntityFromResponse(clientEntity);
    }
}
