package br.com.caseitau.moneytransfer.client.integration;

import br.com.caseitau.moneytransfer.client.controller.CreateClientController;
import br.com.caseitau.moneytransfer.client.dataTest.ClientDataTest;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.dto.CreateClientResponse;
import br.com.caseitau.moneytransfer.client.exception.ResponseError;
import br.com.caseitau.moneytransfer.core.BaseIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.concurrent.ExecutionException;

import static io.restassured.RestAssured.given;


public class CreateClientIntegrationTest extends BaseIntegrationTest {
    private static final String URLPath = "/v1/client";

    @Autowired
    private CreateClientController createClientController;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    void setupBeforeEach() {
        mapperObject = new ObjectMapper();
        clientRepository.deleteAll();
    }

    @Test
    @DisplayName("Given a valid client, when calling the created client controller, then it returns create client.")
    void createClientControllerSuccess() throws ExecutionException, InterruptedException, JsonProcessingException {
        var request = ClientDataTest.basicCreateClientRequestJohnDoe();

        var response = given()
                .header("Content-type", "application/json")
                .body(mapperObject.writeValueAsString(request))
                .when()
                .post(URLPath)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(CreateClientResponse.class);

        Assertions.assertNotNull(response.getId());
        Assertions.assertEquals(request.getName(), response.getName());
        Assertions.assertEquals(request.getAccountNumber(), response.getAccountNumber());
        Assertions.assertEquals(request.getAccountBalance(), response.getAccountBalance());
        Assertions.assertNotNull(response.getCreatedAt());
    }

    @Test
    @DisplayName("Given a client with a account number already exists, when calling the created client controller, then it returns status code 409.")
    void createClientControllerStatusConflict() throws Exception {
        var messageExpected = "Account number already exists.";

        clientRepository.saveAndFlush(ClientDataTest.basicCreateClientEntityJohnDoe());
        var request = ClientDataTest.basicCreateClientRequestJohnDoe();

        var response = given()
                .header("Content-type", "application/json")
                .body(mapperObject.writeValueAsString(request))
                .when()
                .post(URLPath)
                .then()
                .statusCode(HttpStatus.CONFLICT.value())
                .extract()
                .as(ResponseError.class);

        Assertions.assertEquals(messageExpected, response.getMessage());
    }
}
