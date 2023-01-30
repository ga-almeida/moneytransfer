package br.com.caseitau.moneytransfer.client.dataTest;

import br.com.caseitau.moneytransfer.client.dto.CreateClientDTO;

import static br.com.caseitau.moneytransfer.client.unit.CreateClientUseCase.CreateClientConstants.*;

public final class CreateClientDataTest {
    public static CreateClientDTO basicCreateClientDTO() {
        return CreateClientDTO.builder()
                .name(NAME_JOHN_DOE)
                .accountNumber(ACCOUNT_NUMBER_JOHN_DOE)
                .accountBalance(ACCOUNT_BALANCE_JOHN_DOE)
                .build();
    }
}
