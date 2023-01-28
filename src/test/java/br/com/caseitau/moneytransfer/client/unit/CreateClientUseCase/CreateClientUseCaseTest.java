package br.com.caseitau.moneytransfer.client.unit.CreateClientUseCase;

import br.com.caseitau.moneytransfer.client.useCases.createClientUseCase.CreateClientUseCase;
import br.com.caseitau.moneytransfer.core.BaseUnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateClientUseCaseTest extends BaseUnitTest {
    private CreateClientUseCase sut;

    @BeforeEach
    void setupEach() {
        sut = new CreateClientUseCase();
    }

    @Test
    void givenCreateClientValid_WhenCallCreateClientUseCase_ThenReturnSuccess() {
        var createClientDTO = CreateClientDataTest.validCreateClientDTO();

        var createClientResponse = sut.execute(createClientDTO);

        assertEquals(createClientDTO.getName(), createClientResponse.getName());
        assertEquals(createClientDTO.getAccountNumber(), createClientResponse.getAccountNumber());
        assertEquals(createClientDTO.getAccountBalance(), createClientResponse.getAccountBalance());
        assertNull(createClientResponse.getId());
    }
}
