package br.com.caseitau.moneytransfer.client.integration;

import br.com.caseitau.moneytransfer.client.dataTest.ClientDataTest;
import br.com.caseitau.moneytransfer.client.dataTest.TransferDataTest;
import br.com.caseitau.moneytransfer.client.domain.entity.TransferEntity;
import br.com.caseitau.moneytransfer.client.domain.model.StatusEnum;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.domain.repository.TransferRepository;
import br.com.caseitau.moneytransfer.client.dto.CreateClientResponse;
import br.com.caseitau.moneytransfer.client.dto.CreateTransferRequest;
import br.com.caseitau.moneytransfer.client.dto.CreateTransferResponse;
import br.com.caseitau.moneytransfer.client.exception.ResponseError;
import br.com.caseitau.moneytransfer.core.BaseIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateTransferIntegrationTest extends BaseIntegrationTest {
    private static final String URLPath = "/v1/transfer";

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransferRepository transferRepository;

    @BeforeEach
    void setupBeforeEach() {
        mapperObject = new ObjectMapper();
        transferRepository.deleteAll();
        clientRepository.deleteAll();
    }

    @Test
    @DisplayName("Given a transfer, when calling the create transfer controller, then it returns status 201.")
    void createTransferControllerStatusCreated() throws JsonProcessingException {
        this.createClients();

        var request = TransferDataTest.basicCreateTransferRequest();

        var response = given()
                .header("Content-type", "application/json")
                .body(mapperObject.writeValueAsString(request))
                .when()
                .post(URLPath)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(CreateTransferResponse.class);

        assertNotNull(response.getId());
        assertEquals(request.getFromClientAccountNumber(), response.getFromClientAccountNumber());
        assertEquals(request.getOriginClientAccountNumber(), response.getOriginClientAccountNumber());
        assertEquals(request.getValue(), response.getValue());
        assertEquals(StatusEnum.SUCCESS, response.getStatus());
        assertNotNull(response.getCreatedAt());
    }

    @Test
    @DisplayName("Given a transfer with origin client not exists, when calling the create transfer controller, then it returns status 404.")
    void createTransferControllerStatusNotFoundOrigin() throws JsonProcessingException {
        var messageExpected = "Client not exists.";
        var transferCreatedExpected = 1;

        clientRepository.saveAndFlush(ClientDataTest.basicCreateClientEntityJohnDoe());
        var request = TransferDataTest.basicCreateTransferRequest();

        var response = given()
                .header("Content-type", "application/json")
                .body(mapperObject.writeValueAsString(request))
                .when()
                .post(URLPath)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .extract()
                .as(ResponseError.class);

        var listTransfer = transferRepository.findAll();

        assertEquals(messageExpected, response.getMessage());
        assertEquals(transferCreatedExpected, listTransfer.size());
    }

    @Test
    @DisplayName("Given a transfer with from client not exists, when calling the create transfer controller, then it returns status 404.")
    void createTransferControllerStatusNotFoundFrom() throws JsonProcessingException {
        var messageExpected = "Client not exists.";
        var transferCreatedExpected = 1;

        clientRepository.saveAndFlush(ClientDataTest.basicCreateClientEntityJaneDoe());
        var request = TransferDataTest.basicCreateTransferRequest();

        var response = given()
                .header("Content-type", "application/json")
                .body(mapperObject.writeValueAsString(request))
                .when()
                .post(URLPath)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .extract()
                .as(ResponseError.class);

        var listTransfer = transferRepository.findAll();

        assertEquals(messageExpected, response.getMessage());
        assertEquals(transferCreatedExpected, listTransfer.size());
    }
    @Test
    @DisplayName("Given a transfer with a value greater than balance origin client, when calling the created transfer controller, then it returns status 400.")
    void createTransferControllerStatusBadRequest() throws JsonProcessingException {
        var messageExpected = "Transfer value is higher than the account.";
        var transferCreatedExpected = 1;

        this.createClients();

        var request = TransferDataTest.basicCreateTransferRequestWithValueHigherAccount();

        var response = given()
                .header("Content-type", "application/json")
                .body(mapperObject.writeValueAsString(request))
                .when()
                .post(URLPath)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .as(ResponseError.class);

        var listTransfer = transferRepository.findAll();

        assertEquals(messageExpected, response.getMessage());
        assertEquals(transferCreatedExpected, listTransfer.size());
    }

    private void createClients() {
        clientRepository.saveAndFlush(ClientDataTest.basicCreateClientEntityJohnDoe());
        clientRepository.saveAndFlush(ClientDataTest.basicCreateClientEntityJaneDoe());
    }
}
