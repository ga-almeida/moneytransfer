package br.com.caseitau.moneytransfer.client.unit;

import br.com.caseitau.moneytransfer.client.dataTest.ClientDataTest;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepositoryInMemory;
import br.com.caseitau.moneytransfer.client.exception.ClientNotFoundException;
import br.com.caseitau.moneytransfer.client.useCases.FindOneClientUseCase;
import br.com.caseitau.moneytransfer.core.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@BaseUnitTest
public class findOneClientUseCaseTest {
    private FindOneClientUseCase sut;

    private ClientRepository clientRepository;

    @BeforeEach
    void setupEach() {
        clientRepository = new ClientRepositoryInMemory();
        sut = new FindOneClientUseCase(clientRepository);
    }

    @Test
    @DisplayName("Given a client exists, when calling find one client use case, then it returns client.")
    void findOneClientUseCaseSuccess() {
        var createClientDTO = ClientDataTest.basicCreateClientJohnDoe();
        clientRepository.save(createClientDTO);

        var findOneClientResponse = sut.execute(createClientDTO.getAccountNumber());

        assertEquals(createClientDTO.getName(), findOneClientResponse.getName());
        assertEquals(createClientDTO.getAccountNumber(), findOneClientResponse.getAccountNumber());
        assertEquals(createClientDTO.getAccountBalance(), findOneClientResponse.getAccountBalance());
        assertNotNull(findOneClientResponse.getCreatedAt());
        assertNotNull(findOneClientResponse.getUpdatedAt());
        assertNotNull(findOneClientResponse.getId());
    }

    @Test
    @DisplayName("Given a client not exists, when calling find one client use case, then it returns an exception of client not found.")
    void findOneClientUseCaseClientNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> sut.execute("client-not-exists"));
    }
}
