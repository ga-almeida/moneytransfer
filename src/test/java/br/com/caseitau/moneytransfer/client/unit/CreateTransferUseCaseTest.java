package br.com.caseitau.moneytransfer.client.unit;

import br.com.caseitau.moneytransfer.client.dataTest.TransferDataTest;
import br.com.caseitau.moneytransfer.client.domain.repository.TransferRepository;
import br.com.caseitau.moneytransfer.client.useCases.CreateTransferUseCase;
import br.com.caseitau.moneytransfer.core.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@BaseUnitTest
public class CreateTransferUseCaseTest {
    private CreateTransferUseCase sut;

    @Mock
    private TransferRepository transferRepository;

    @BeforeEach
    void setupEach() {
        sut = new CreateTransferUseCase(transferRepository);
    }

    @Test
    @DisplayName("Given a transfer, when calling the transfer use case, then it returns a registered transfer.")
    void transferUseCaseSuccess() {
        var createTransferRequest = TransferDataTest.basicCreateTransferRequest();

        when(transferRepository.save(createTransferRequest))
                .thenReturn(TransferDataTest.basicCreateTransferEntity());
        var transferBalanceResponse = sut.execute(createTransferRequest);

        assertNotNull(transferBalanceResponse.getId());
        assertEquals(createTransferRequest.getOriginClientId(), transferBalanceResponse.getOriginClientId());
        assertEquals(createTransferRequest.getFromClientId(), transferBalanceResponse.getFromClientId());
        assertEquals(createTransferRequest.getBalance(), transferBalanceResponse.getBalance());
        assertNotNull(transferBalanceResponse.getCreatedAt());
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
