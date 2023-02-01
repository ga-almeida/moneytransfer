package br.com.caseitau.moneytransfer.client.unit;

import br.com.caseitau.moneytransfer.client.dataTest.ClientDataTest;
import br.com.caseitau.moneytransfer.client.domain.repository.IClientService;
import br.com.caseitau.moneytransfer.client.useCases.ListClientsUseCase;
import br.com.caseitau.moneytransfer.core.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ListClientsUseCaseTest extends BaseUnitTest {
    private ListClientsUseCase sut;

    @Mock
    private IClientService clientService;

    @BeforeEach
    void setupEach() {
        sut = new ListClientsUseCase(clientService);
    }

    @Test
    @DisplayName("When calling list clients use case, then it returns all clients.")
    void listClientsUseCaseSuccess() {
        when(clientService.findAll()).thenReturn(ClientDataTest.basicCreateFiveClients());
        var listClientsResponse = sut.execute();

        assertEquals(5, listClientsResponse.getClients().size());
    }
}
