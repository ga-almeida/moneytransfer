package br.com.caseitau.moneytransfer.client.unit;

import br.com.caseitau.moneytransfer.client.dataTest.ClientDataTest;
import br.com.caseitau.moneytransfer.client.dataTest.TransferDataTest;
import br.com.caseitau.moneytransfer.client.domain.model.StatusEnum;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.domain.repository.TransferRepository;
import br.com.caseitau.moneytransfer.client.exception.ClientNotFoundException;
import br.com.caseitau.moneytransfer.client.exception.ValueIsHigherThanAccountExcepetion;
import br.com.caseitau.moneytransfer.client.useCases.CreateTransferUseCase;
import br.com.caseitau.moneytransfer.core.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@BaseUnitTest
public class CreateTransferUseCaseTest {
    private CreateTransferUseCase sut;

    @Mock
    private TransferRepository transferRepository;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void setupEach() {
        sut = new CreateTransferUseCase(transferRepository, clientRepository);
    }

    @Test
    @DisplayName("Given a transfer, when calling the transfer use case, then it returns a registered transfer.")
    void transferUseCaseSuccess() {
        var createTransferRequest = TransferDataTest.basicCreateTransferRequest();

        when(clientRepository.findOne(createTransferRequest.getOriginClientAccountNumber()))
                .thenReturn(ClientDataTest.basicFindOneClientEntityJaneDoe());
        when(transferRepository.save(createTransferRequest))
                .thenReturn(TransferDataTest.basicCreateTransferEntity());
        var transferResponse = sut.execute(createTransferRequest);

        assertNotNull(transferResponse.getId());
        assertEquals(createTransferRequest.getOriginClientAccountNumber(), transferResponse.getOriginClientAccountNumber());
        assertEquals(createTransferRequest.getFromClientAccountNumber(), transferResponse.getFromClientAccountNumber());
        assertEquals(createTransferRequest.getValue(), transferResponse.getValue());
        assertEquals(StatusEnum.SUCCESS, transferResponse.getStatus());
        assertNotNull(transferResponse.getCreatedAt());
    }

    @Test
    @DisplayName("Given a transfer with a value greater than balance origin client, when calling the created transfer use case, then it returns an exception of the value is higher than account.")
    void createTransferUseCaseValueIsHigherThanAccountExcepetion() {
        var createTransferRequestWithValueHigherAccount = TransferDataTest.basicCreateTransferRequestWithValueHigherAccount();
        when(clientRepository.findOne(createTransferRequestWithValueHigherAccount.getOriginClientAccountNumber()))
                .thenReturn(ClientDataTest.basicFindOneClientEntityJaneDoe());
        assertThrows(ValueIsHigherThanAccountExcepetion.class, () -> sut.execute(createTransferRequestWithValueHigherAccount));
    }

    @Test
    @DisplayName("Given a transfer with a origin and from client not exists, when calling the created transfer use case, then it returns an exception of client not found.")
    void createTransferUseCaseOriginClientNotFoundExcepetion() {
        var createTransferRequest = TransferDataTest.basicCreateTransferRequest();
        when(clientRepository.findOne(createTransferRequest.getOriginClientAccountNumber()))
                .thenReturn(Optional.empty());
        assertThrows(ClientNotFoundException.class, () -> sut.execute(createTransferRequest));
    }

    @Test
    @DisplayName("Given a transfer with a from client not exists, when calling the created transfer use case, then it returns an exception of client not found.")
    void createTransferUseCaseFromClientNotFoundExcepetion() {
        var createTransferRequest = TransferDataTest.basicCreateTransferRequest();
        lenient().when(clientRepository.findOne(createTransferRequest.getFromClientAccountNumber()))
                .thenReturn(Optional.empty());
        assertThrows(ClientNotFoundException.class, () -> sut.execute(createTransferRequest));
    }
}
