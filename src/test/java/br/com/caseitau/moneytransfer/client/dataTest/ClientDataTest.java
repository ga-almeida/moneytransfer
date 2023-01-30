package br.com.caseitau.moneytransfer.client.dataTest;

import br.com.caseitau.moneytransfer.client.dto.ClientDTO;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static br.com.caseitau.moneytransfer.client.dataTest.ClientConstants.*;

public final class ClientDataTest {
    public static ClientDTO basicCreateClientJohnDoe() {
        return ClientDTO.builder()
                .name(NAME_JOHN_DOE)
                .accountNumber(ACCOUNT_NUMBER_JOHN_DOE)
                .accountBalance(ACCOUNT_BALANCE_JOHN_DOE)
                .build();
    }

    public static ClientDTO basicCreateClientJaneDoe() {
        return ClientDTO.builder()
                .name(NAME_JANE_DOE)
                .accountNumber(ACCOUNT_NUMBER_JANE_DOE)
                .accountBalance(ACCOUNT_BALANCE_JANE_DOE)
                .build();
    }

    public static ClientDTO basicCreateClientCustom(String name, String accountNumber, BigDecimal accountBalance) {
        return ClientDTO.builder()
                .name(name)
                .accountNumber(accountNumber)
                .accountBalance(accountBalance)
                .createdAt(ZonedDateTime.now())
                .updateAt(ZonedDateTime.now())
                .build();
    }
}
