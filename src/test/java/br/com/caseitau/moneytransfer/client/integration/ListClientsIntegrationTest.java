package br.com.caseitau.moneytransfer.client.integration;

import br.com.caseitau.moneytransfer.client.dataTest.ClientDataTest;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.dto.ListClientsResponse;
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

public class ListClientsIntegrationTest extends BaseIntegrationTest {
    private static final String URLPath = "/v1/client";

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
    @DisplayName("When calling list clients controller, then it returns status code 200.")
    void listClientsControllerStatusOk() {
        var sizeListClientsExpected = 5;
        clientRepository.saveAllAndFlush(ClientDataTest.basicCreateFiveCustomClients());

        var response = given()
                .header("Content-type", "application/json")
                .when()
                .get(URLPath)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ListClientsResponse.class);

        assertEquals(sizeListClientsExpected, response.getClients().size());
    }
}
