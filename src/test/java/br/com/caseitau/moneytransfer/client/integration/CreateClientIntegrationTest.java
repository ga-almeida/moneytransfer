package br.com.caseitau.moneytransfer.client.integration;

import br.com.caseitau.moneytransfer.client.controller.CreateClientController;
import br.com.caseitau.moneytransfer.client.dataTest.ClientDataTest;
import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepository;
import br.com.caseitau.moneytransfer.client.dto.CreateClientRequest;
import br.com.caseitau.moneytransfer.client.dto.CreateClientResponse;
import br.com.caseitau.moneytransfer.core.BaseIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@BaseIntegrationTest
public class CreateClientIntegrationTest {
    private static final HttpStatusCode HTTP_CODE_CREATED = HttpStatusCode.valueOf(201);
    private static final HttpStatusCode HTTP_CODE_CONFLIC = HttpStatusCode.valueOf(409);

    @Autowired
    private CreateClientController createClientController;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    void setupBeforeEach() {
        clientRepository.deleteAll();
    }

    @Test
    @DisplayName("Given a valid client, when calling the created client controller, then it returns create client.")
    void createClientControllerSuccess() throws ExecutionException, InterruptedException {
        var createClientRequest = ClientDataTest.basicCreateClientRequestJohnDoe();

        var response = createClientController.createClient(createClientRequest);

        assertEquals(HTTP_CODE_CREATED, response.get().getStatusCode());
        assertEquals(createClientRequest.getName(), response.get().getBody().getName());
        assertEquals(createClientRequest.getAccountNumber(), response.get().getBody().getAccountNumber());
        assertEquals(createClientRequest.getAccountBalance(), response.get().getBody().getAccountBalance());
        assertNotNull(response.get().getBody().getId());
        assertNotNull(response.get().getBody().getCreatedAt());
    }

    @Test
    @DisplayName("Given a client with a account number already exists, when calling the created client controller, then it returns status code 409.")
    void createClientControllerStatusConflict() {
        String messageExpected = "Account number already exists.";
        String errorCodeExpected = "409";

        var clientEntity = ClientDataTest.basicCreateClientEntityJohnDoe();
        clientRepository.saveAndFlush(clientEntity);

        var createClientRequest = ClientDataTest.basicCreateClientRequestJohnDoe();
        var response = createClientController.createClient(createClientRequest);

        assertAll(

        );
        assertEquals(HTTP_CODE_CONFLIC, response.get().getStatusCode());
        assertEquals(messageExpected, response.);
        assertEquals(errorCodeExpected, response.get().getBody().getAccountNumber());
    }
}
