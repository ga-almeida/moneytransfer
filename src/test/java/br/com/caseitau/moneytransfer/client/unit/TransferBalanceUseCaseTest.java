package br.com.caseitau.moneytransfer.client.unit;

import br.com.caseitau.moneytransfer.client.dataTest.ClientDataTest;
import br.com.caseitau.moneytransfer.client.dataTest.TransferDataTest;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepositoryInMemory;
import br.com.caseitau.moneytransfer.client.domain.repository.TransferRepository;
import br.com.caseitau.moneytransfer.client.domain.repository.TransferRepositoryInMemory;
import br.com.caseitau.moneytransfer.client.dto.TransferDTO;
import br.com.caseitau.moneytransfer.client.exception.AccountNumberAlreadyExistsExcepetion;
import br.com.caseitau.moneytransfer.client.useCases.TransferBalanceUseCase;
import br.com.caseitau.moneytransfer.core.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@BaseUnitTest
public class TransferBalanceUseCaseTest {
    private TransferBalanceUseCase sut;

    private TransferRepository transferRepository;

    @BeforeEach
    void setupEach() {
        transferRepository = new TransferRepositoryInMemory();
        sut = new TransferBalanceUseCase(transferRepository);
    }

    @Test
    @DisplayName("Given a transfer, when calling the transfer use case, then it returns a registered transfer.")
    void transferUseCaseSuccess() {
        var transferDTO = TransferDataTest.basicTransferBalance();
        var transferBalanceResponse = sut.execute(transferDTO);

        assertNotNull(transferBalanceResponse.getId());
        assertEquals(transferDTO.getOriginClient().getAccountNumber(), transferBalanceResponse.getOriginClient().getAccountNumber());
        assertEquals(transferDTO.getFromClient().getAccountNumber(), transferBalanceResponse.getFromClient().getAccountNumber());
        assertEquals(transferDTO.getBalance(), transferBalanceResponse.getBalance());
        assertNotNull(transferBalanceResponse.getCreatedAt());
        assertNotNull(transferBalanceResponse.getUpdatedAt());
    }

//    @Test
//    @DisplayName("Given a client with a account number already exists, when calling the created client use case, then it returns an exception of account number already registered.")
//    void createClientUseCaseAccountNumberAlreadyExistsExcepetion() {
//        clientRepository.save(ClientDataTest.basicCreateClientJohnDoe());
//
//        var createClientDTO = ClientDataTest.basicCreateClientJohnDoe();
//
//        assertThrows(AccountNumberAlreadyExistsExcepetion.class, () -> sut.execute(createClientDTO));
//    }
}
