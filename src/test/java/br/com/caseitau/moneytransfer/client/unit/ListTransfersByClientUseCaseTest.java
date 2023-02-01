package br.com.caseitau.moneytransfer.client.unit;

import br.com.caseitau.moneytransfer.client.dataTest.ClientDataTest;
import br.com.caseitau.moneytransfer.client.dataTest.TransferDataTest;
import br.com.caseitau.moneytransfer.client.domain.repository.IClientService;
import br.com.caseitau.moneytransfer.client.domain.repository.ITransferService;
import br.com.caseitau.moneytransfer.client.exception.ClientNotFoundException;
import br.com.caseitau.moneytransfer.client.useCases.ListTransfersByClientUseCase;
import br.com.caseitau.moneytransfer.core.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ListTransfersByClientUseCaseTest extends BaseUnitTest {
    private ListTransfersByClientUseCase sut;

    @Mock
    private ITransferService transferService;

    @Mock
    private IClientService clientService;

    @BeforeEach
    void setupEach() {
        sut = new ListTransfersByClientUseCase(transferService, clientService);
    }

    @Test
    @DisplayName("Given a client exists, when calling list transfers by client use case, then it returns all clients' transfers.")
    void listTransfersByClientUseCaseSuccess() {
        var clientEntity = ClientDataTest.basicFindOneClientEntityJaneDoe();
        when(clientService.findOneByAccountNumber(clientEntity.get().getAccountNumber()))
                .thenReturn(clientEntity);
        when(transferService.findByClientId(clientEntity.get().getId()))
                .thenReturn(TransferDataTest.listTransfersbyClient());
        var listClientsResponse = sut.execute(clientEntity.get().getAccountNumber());

        assertEquals(5, listClientsResponse.getTransfers().size());
        assertEquals(clientEntity.get().getAccountNumber(), listClientsResponse.getAccountNumber());
        assertEquals(clientEntity.get().getName(), listClientsResponse.getName());
    }

    @Test
    @DisplayName("Given a client not exists, when calling list transfers by client use case, then it returns an exception of client not found.")
    void listTransfersByClientUseCaseClientNotFoundException() {
        when(clientService.findOneByAccountNumber(anyString()))
                .thenReturn(Optional.empty());
        assertThrows(ClientNotFoundException.class, () -> sut.execute("client-not-exists"));
    }
}
