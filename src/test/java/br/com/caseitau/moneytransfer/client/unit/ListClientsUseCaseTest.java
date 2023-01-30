package br.com.caseitau.moneytransfer.client.unit;

import br.com.caseitau.moneytransfer.client.dataTest.ClientDataTest;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepositoryInMemory;
import br.com.caseitau.moneytransfer.client.useCases.ListClientsUseCase;
import br.com.caseitau.moneytransfer.core.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@BaseUnitTest
public class ListClientsUseCaseTest {
    private ListClientsUseCase sut;

    private ClientRepository clientRepository;

    @BeforeEach
    void setupEach() {
        clientRepository = new ClientRepositoryInMemory();
        sut = new ListClientsUseCase(clientRepository);
    }

    @Test
    @DisplayName("When calling list clients use case, then it returns all clients.")
    void listClientsUseCaseSuccess() {
        for (int i = 0; i < 5; i++) {
            clientRepository.save(ClientDataTest.basicCreateClientJohnDoe());
        }

        var listClientsResponse = sut.execute();

        assertEquals(5, listClientsResponse.size());
    }
}
