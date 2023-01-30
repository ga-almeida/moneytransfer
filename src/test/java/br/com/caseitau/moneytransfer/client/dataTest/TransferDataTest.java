package br.com.caseitau.moneytransfer.client.dataTest;

import br.com.caseitau.moneytransfer.client.dto.ClientDTO;
import br.com.caseitau.moneytransfer.client.dto.TransferDTO;

import java.math.BigDecimal;

import static br.com.caseitau.moneytransfer.client.dataTest.ClientConstants.*;
import static br.com.caseitau.moneytransfer.client.dataTest.ClientDataTest.*;

public final class TransferDataTest {
    public static TransferDTO basicTransferBalance() {
        return TransferDTO.builder()
                .fromClient(basicCreateClientJohnDoe())
                .originClient(basicCreateClientJaneDoe())
                .balance(BigDecimal.valueOf(30))
                .build();
    }
}
