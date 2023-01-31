package br.com.caseitau.moneytransfer.client.unit;

import br.com.caseitau.moneytransfer.client.dataTest.ClientDataTest;
import br.com.caseitau.moneytransfer.client.domain.repository.IClientService;
import br.com.caseitau.moneytransfer.client.exception.AccountNumberAlreadyExistsExcepetion;
import br.com.caseitau.moneytransfer.client.useCases.CreateClientUseCase;
import br.com.caseitau.moneytransfer.core.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@BaseUnitTest
public class CreateClientUseCaseTest {
    private CreateClientUseCase sut;

    @Mock
    private IClientService clientService;

    @BeforeEach
    void setupEach() {
        sut = new CreateClientUseCase(clientService);
    }

    @Test
    @DisplayName("Given a valid client, when calling the created client use case, then it returns a registered client.")
    void createClientUseCaseSuccess() {
        var createClientRequest = ClientDataTest.basicCreateClientRequestJohnDoe();

        when(clientService.save(createClientRequest)).thenReturn(ClientDataTest.basicCreateClientEntityJohnDoe());
        var createClientResponse = sut.execute(createClientRequest);

        assertEquals(createClientRequest.getName(), createClientResponse.getName());
        assertEquals(createClientRequest.getAccountNumber(), createClientResponse.getAccountNumber());
        assertEquals(createClientRequest.getAccountBalance(), createClientResponse.getAccountBalance());
        assertNotNull(createClientResponse.getCreatedAt());
        assertNotNull(createClientResponse.getId());
    }

    @Test
    @DisplayName("Given a client with a account number already exists, when calling the created client use case, then it returns an exception of account number already registered.")
    void createClientUseCaseAccountNumberAlreadyExistsExcepetion() {
        var createClientRequest = ClientDataTest.basicCreateClientRequestJohnDoe();
        when(clientService.findClientByAccountNumber(createClientRequest.getAccountNumber()))
                .thenReturn(Boolean.TRUE);

        assertThrows(AccountNumberAlreadyExistsExcepetion.class, () -> sut.execute(createClientRequest));
    }
}
