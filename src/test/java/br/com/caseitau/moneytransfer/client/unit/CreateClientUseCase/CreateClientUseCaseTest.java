package br.com.caseitau.moneytransfer.client.unit.CreateClientUseCase;

import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.useCases.createClientUseCase.CreateClientUseCase;
import br.com.caseitau.moneytransfer.core.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class CreateClientUseCaseTest extends BaseUnitTest {
    private CreateClientUseCase sut;
    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void setupEach() {
        sut = new CreateClientUseCase(clientRepository);
    }

    @Test
    void givenCreateClientValid_WhenCallCreateClientUseCase_ThenReturnSuccess() {
        var createClientDTO = CreateClientDataTest.validCreateClientDTO();

        when(clientRepository.save(createClientDTO)).thenReturn(CreateClientDataTest.clientPersistSaved());
        var createClientResponse = sut.execute(createClientDTO);

        assertEquals(createClientDTO.getName(), createClientResponse.getName());
        assertEquals(createClientDTO.getAccountNumber(), createClientResponse.getAccountNumber());
        assertEquals(createClientDTO.getAccountBalance(), createClientResponse.getAccountBalance());
        assertNotNull(createClientResponse.getId());
        verify(clientRepository, times(1)).save(createClientDTO);
    }
}
