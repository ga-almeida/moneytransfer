package br.com.caseitau.moneytransfer.client.controller;

import br.com.caseitau.moneytransfer.client.dto.CreateTransferResponse;
import br.com.caseitau.moneytransfer.client.dto.FindOneClientResponse;
import br.com.caseitau.moneytransfer.client.dto.ListClientsResponse;
import br.com.caseitau.moneytransfer.client.exception.ResponseError;
import br.com.caseitau.moneytransfer.client.useCases.ListClientsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@Tag(name = "Clients")
@RequestMapping("/client")
public class ListClientsController {
    private final Executor controllersExecutor;
    private final ListClientsUseCase listClientsUseCase;

    public ListClientsController(Executor controllersExecutor, ListClientsUseCase listClientsUseCase) {
        this.controllersExecutor = controllersExecutor;
        this.listClientsUseCase = listClientsUseCase;
    }

    @Operation(summary = "Get all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get All Client Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListClientsResponse.class))}
            )
    })
    @GetMapping
    public CompletableFuture<ResponseEntity<ListClientsResponse>> listClients() {
        return supplyAsync(() -> listClientsUseCase.execute(), controllersExecutor)
                .thenApply(ResponseEntityTypes::ok);
    }
}
