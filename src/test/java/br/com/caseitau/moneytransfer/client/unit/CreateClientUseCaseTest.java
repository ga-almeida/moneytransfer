package br.com.caseitau.moneytransfer.client.unit;

import br.com.caseitau.moneytransfer.client.useCase.CreateClientUseCase;
import br.com.caseitau.moneytransfer.core.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;

public class CreateClientUseCaseTest extends BaseUnitTest {
    private CreateClientUseCase sut;

    @BeforeEach
    void setupEach() {
        sut = new CreateClientUseCase();
    }
}
