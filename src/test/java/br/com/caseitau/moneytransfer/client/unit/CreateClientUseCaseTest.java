package br.com.caseitau.moneytransfer.client.unit;

import br.com.caseitau.moneytransfer.client.dataTest.ClientDataTest;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepositoryInMemory;
import br.com.caseitau.moneytransfer.client.exception.AccountNumberAlreadyExistsExcepetion;
import br.com.caseitau.moneytransfer.client.useCases.CreateClientUseCase;
import br.com.caseitau.moneytransfer.core.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@BaseUnitTest
public class CreateClientUseCaseTest {
    private CreateClientUseCase sut;
    private ClientRepository clientRepository;

    @BeforeEach
    void setupEach() {
        clientRepository = new ClientRepositoryInMemory();
        sut = new CreateClientUseCase(clientRepository);
    }

    @Test
    @DisplayName("Given a valid client, when calling the created client use case, then it returns a registered client.")
    void createClientUseCaseSuccess() {
        var createClientDTO = ClientDataTest.basicCreateClientJohnDoe();

        var createClientResponse = sut.execute(createClientDTO);

        assertEquals(createClientDTO.getName(), createClientResponse.getName());
        assertEquals(createClientDTO.getAccountNumber(), createClientResponse.getAccountNumber());
        assertEquals(createClientDTO.getAccountBalance(), createClientResponse.getAccountBalance());
        assertNotNull(createClientResponse.getCreatedAt());
        assertNotNull(createClientResponse.getId());
    }

    @Test
    @DisplayName("Given a client with a account number already exists, when calling the created client use case, then it returns an exception of account number already registered.")
    void createClientUseCaseAccountNumberAlreadyExistsExcepetion() {
        clientRepository.save(ClientDataTest.basicCreateClientJohnDoe());

        var createClientDTO = ClientDataTest.basicCreateClientJohnDoe();

        assertThrows(AccountNumberAlreadyExistsExcepetion.class, () -> sut.execute(createClientDTO));
    }
}
