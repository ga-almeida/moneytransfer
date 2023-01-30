package br.com.caseitau.moneytransfer.client.dataTest;

import br.com.caseitau.moneytransfer.client.dto.CreateClientDTO;

import static br.com.caseitau.moneytransfer.client.dataTest.ClientConstants.*;

public final class ClientDataTest {
    public static CreateClientDTO basicCreateClientDTO() {
        return CreateClientDTO.builder()
                .name(NAME_JOHN_DOE)
                .accountNumber(ACCOUNT_NUMBER_JOHN_DOE)
                .accountBalance(ACCOUNT_BALANCE_JOHN_DOE)
                .build();
    }
}
