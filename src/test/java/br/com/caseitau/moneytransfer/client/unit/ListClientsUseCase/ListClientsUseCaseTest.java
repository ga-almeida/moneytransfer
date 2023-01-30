package br.com.caseitau.moneytransfer.client.unit.ListClientsUseCase;

import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.core.BaseUnitTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@BaseUnitTest
public class ListClientsUseCaseTest {
//    private ListClientsUseCase sut;

//    @Autowired
//    @Qualifier("ClientRepositoryInMemory")
//    private ClientRepository clientRepository;

//    @BeforeEach
//    void setupEach() {
//        sut = new ListClientsUseCase(clientRepository);
//    }
//
//    @Test
//    @DisplayName("Given a valid client, when calling the created client use case, then it returns a registered client.")
//    void createClientUseCaseSuccess() {
//        var createClientDTO = CreateClientDataTest.basicCreateClientDTO();
//
//        var createClientResponse = sut.execute(createClientDTO);
//
//        assertEquals(createClientDTO.getName(), createClientResponse.getName());
//        assertEquals(createClientDTO.getAccountNumber(), createClientResponse.getAccountNumber());
//        assertEquals(BigDecimal.ZERO, createClientResponse.getAccountBalance());
//        assertNotNull(createClientResponse.getId());
//    }
}
