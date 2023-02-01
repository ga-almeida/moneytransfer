package br.com.caseitau.moneytransfer.client.integration;

import br.com.caseitau.moneytransfer.client.dataTest.ClientDataTest;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.dto.FindOneClientResponse;
import br.com.caseitau.moneytransfer.client.exception.ResponseError;
import br.com.caseitau.moneytransfer.core.BaseIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindOneClientIntegrationTest extends BaseIntegrationTest {
    private static final String URLPath = "/v1/client/{accountNumber}";

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    void setupBeforeEach() {
        mapperObject = new ObjectMapper();
    }

    @AfterEach
    void setupAfterEach() {
        clientRepository.deleteAll();
    }

    @Test
    @DisplayName("Given a client exists, when calling find one client controller, then it returns status code 200.")
    void findOneClientControllerStatusOk() {
        var clientEntity = clientRepository.saveAndFlush(ClientDataTest.basicCreateClientEntityJohnDoe());

        var response = given()
                .header("Content-type", "application/json")
                .pathParams("accountNumber", clientEntity.getAccountNumber())
                .when()
                .get(URLPath)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(FindOneClientResponse.class);

        assertEquals(clientEntity.getId(), response.getId());
        assertEquals(clientEntity.getName(), response.getName());
        assertEquals(clientEntity.getAccountNumber(), response.getAccountNumber());
        assertEquals(clientEntity.getAccountBalance(), response.getAccountBalance());
        assertEquals(clientEntity.getCreatedAt().toLocalDate().atStartOfDay(), response.getCreatedAt().toLocalDate().atStartOfDay());
    }

    @Test
    @DisplayName("Given a client not exists, when calling find one client controller, then it returns status code 404.")
    void findOneClientControllerStatusNotFound() {
        var messageExpected = "Client not exists.";

        var response = given()
                .header("Content-type", "application/json")
                .pathParams("accountNumber", "client-not-exists")
                .when()
                .get(URLPath)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .extract()
                .as(ResponseError.class);

        assertEquals(messageExpected, response.getMessage());
    }
}
