package br.com.caseitau.moneytransfer.client.dataTest;

import br.com.caseitau.moneytransfer.client.dto.CreateClientDTO;

import java.math.BigDecimal;

import static br.com.caseitau.moneytransfer.client.unit.CreateClientUseCase.CreateClientConstants.ACCOUNT_NUMBER_JOHN_DOE;
import static br.com.caseitau.moneytransfer.client.unit.CreateClientUseCase.CreateClientConstants.NAME_JOHN_DOE;

public final class CreateClientDataTest {
    public static CreateClientDTO basicCreateClientDTO() {
        return CreateClientDTO.builder()
                .name(NAME_JOHN_DOE)
                .accountNumber(ACCOUNT_NUMBER_JOHN_DOE)
                .accountBalance(BigDecimal.ZERO)
                .build();
    }
}
