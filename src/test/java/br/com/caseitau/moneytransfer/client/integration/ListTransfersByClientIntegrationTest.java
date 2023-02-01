package br.com.caseitau.moneytransfer.client.integration;

import br.com.caseitau.moneytransfer.client.dataTest.ClientDataTest;
import br.com.caseitau.moneytransfer.client.dataTest.TransferConstants;
import br.com.caseitau.moneytransfer.client.dataTest.TransferDataTest;
import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.domain.repository.TransferRepository;
import br.com.caseitau.moneytransfer.client.dto.ListClientsResponse;
import br.com.caseitau.moneytransfer.client.dto.ListTransfersByClientResponse;
import br.com.caseitau.moneytransfer.client.exception.ResponseError;
import br.com.caseitau.moneytransfer.core.BaseIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListTransfersByClientIntegrationTest extends BaseIntegrationTest {
    private static final String URLPath = "/v1/transfer";

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransferRepository transferRepository;

    @BeforeEach
    void setupBeforeEach() {
        mapperObject = new ObjectMapper();
    }

    @AfterEach
    void setupAfterEach() {
        transferRepository.deleteAll();
        clientRepository.deleteAll();
    }

    @Test
    @DisplayName("Given a client exists, when calling list transfers by client controller, then it returns 200.")
    void listTransfersByClientControllerStatusOk() {
        var sizeListTransferExpected = 5;
        var nameClientTransferExpected = TransferConstants.ORIGIN_CLIENT_NAME;
        var accountNumberClientTransferExpected = TransferConstants.ORIGIN_CLIENT_ACCOUNT_NUMBER;

        var clientEntityOrigin = clientRepository.saveAndFlush(ClientDataTest.basicCreateClientEntityJaneDoe());
        var clientEntityFrom = clientRepository.saveAndFlush(ClientDataTest.basicCreateClientEntityJohnDoe());
        transferRepository.saveAllAndFlush(TransferDataTest.listTransfersbyClientCustom(clientEntityOrigin.getId(), clientEntityFrom.getId()));

        var response = given()
                .header("Content-type", "application/json")
                .queryParam("accountNumber", accountNumberClientTransferExpected)
                .when()
                .get(URLPath)
                .then()
                .statusCode(HttpStatus.OK.value())
                .log().all()
                .extract()
                .as(ListTransfersByClientResponse.class);

        assertEquals(sizeListTransferExpected, response.getTransfers().size());
        assertEquals(nameClientTransferExpected, response.getName());
        assertEquals(accountNumberClientTransferExpected, response.getAccountNumber());
    }

    @Test
    @DisplayName("Given a client not exists, when calling list transfers by client controller, then it returns 404.")
    void listTransfersByClientControllerStatusNotFound() {
        var messageExpected = "Client not exists.";

        var response = given()
                .header("Content-type", "application/json")
                .queryParam("accountNumber", TransferConstants.ORIGIN_CLIENT_ACCOUNT_NUMBER)
                .when()
                .get(URLPath)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .extract()
                .as(ResponseError.class);

        assertEquals(messageExpected, response.getMessage());
    }
}
