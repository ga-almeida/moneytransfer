package br.com.caseitau.moneytransfer.client.unit.CreateClientUseCase;

import br.com.caseitau.moneytransfer.client.dataTest.CreateClientDataTest;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.exception.AccountNumberAlreadyExistsExcepetion;
import br.com.caseitau.moneytransfer.client.repository.ClientRepositoryInMemory;
import br.com.caseitau.moneytransfer.client.useCases.CreateClientUseCase;
import br.com.caseitau.moneytransfer.core.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
        var createClientDTO = CreateClientDataTest.basicCreateClientDTO();

        var createClientResponse = sut.execute(createClientDTO);

        assertEquals(createClientDTO.getName(), createClientResponse.getName());
        assertEquals(createClientDTO.getAccountNumber(), createClientResponse.getAccountNumber());
        assertEquals(BigDecimal.ZERO, createClientResponse.getAccountBalance());
        assertNotNull(createClientResponse.getId());
    }

    @Test
    @DisplayName("Given a client with a account number already registered, when calling the created client use case, then it returns an exception of account number already registered.")
    void createClientUseCaseAccountNumberAlreadyExistsExcepetion() {
        clientRepository.save(CreateClientDataTest.basicCreateClientDTO());

        var createClientDTO = CreateClientDataTest.basicCreateClientDTO();

        assertThrows(AccountNumberAlreadyExistsExcepetion.class, () -> sut.execute(createClientDTO));
    }
}
