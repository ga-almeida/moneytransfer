package br.com.caseitau.moneytransfer.client.unit.CreateClientUseCase;

import br.com.caseitau.moneytransfer.client.useCases.createClientUseCase.CreateClientDTO;

import java.util.UUID;

import static br.com.caseitau.moneytransfer.client.unit.CreateClientUseCase.CreateClientConstants.*;

public final class CreateClientDataTest {
    public static CreateClientDTO validCreateClientDTO() {
        return CreateClientDTO.builder()
                .name(NAME_JOHN_DOE)
                .accountNumber(ACCOUNT_NUMBER_JOHN_DOE)
                .accountBalance(ACCOUNT_BALANCE_JOHN_DOE)
                .build();
    }
}
