package br.com.caseitau.moneytransfer.client.unit;

import br.com.caseitau.moneytransfer.client.dataTest.ClientDataTest;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.exception.ClientNotFoundException;
import br.com.caseitau.moneytransfer.client.useCases.FindOneClientUseCase;
import br.com.caseitau.moneytransfer.core.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@BaseUnitTest
public class findOneClientUseCaseTest {
    private FindOneClientUseCase sut;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void setupEach() {
        sut = new FindOneClientUseCase(clientRepository);
    }

    @Test
    @DisplayName("Given a client exists, when calling find one client use case, then it returns client.")
    void findOneClientUseCaseSuccess() {
        var createClientRequest = ClientDataTest.basicCreateClientRequestJohnDoe();
        when(clientRepository.findOne(createClientRequest.getAccountNumber()))
                .thenReturn(ClientDataTest.basicFindOneClientEntityJohnDoe());

        var findOneClientResponse = sut.execute(createClientRequest.getAccountNumber());

        assertEquals(createClientRequest.getName(), findOneClientResponse.getName());
        assertEquals(createClientRequest.getAccountNumber(), findOneClientResponse.getAccountNumber());
        assertEquals(createClientRequest.getAccountBalance(), findOneClientResponse.getAccountBalance());
        assertNotNull(findOneClientResponse.getCreatedAt());
        assertNotNull(findOneClientResponse.getUpdatedAt());
        assertNotNull(findOneClientResponse.getId());
    }

    @Test
    @DisplayName("Given a client not exists, when calling find one client use case, then it returns an exception of client not found.")
    void findOneClientUseCaseClientNotFoundException() {
        when(clientRepository.findOne(anyString()))
                .thenReturn(Optional.empty());
        assertThrows(ClientNotFoundException.class, () -> sut.execute("client-not-exists"));
    }
}
