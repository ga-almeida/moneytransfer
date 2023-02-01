package br.com.caseitau.moneytransfer.client.controller;

import br.com.caseitau.moneytransfer.client.dto.FindOneClientResponse;
import br.com.caseitau.moneytransfer.client.dto.ListClientsResponse;
import br.com.caseitau.moneytransfer.client.dto.ListTransfersByClientResponse;
import br.com.caseitau.moneytransfer.client.exception.ResponseError;
import br.com.caseitau.moneytransfer.client.useCases.ListClientsUseCase;
import br.com.caseitau.moneytransfer.client.useCases.ListTransfersByClientUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@Tag(name = "Transfer")
@RequestMapping("/transfer")
public class ListTransferByClientController {
    private final Executor controllersExecutor;
    private final ListTransfersByClientUseCase listTransfersByClientUseCase;

    public ListTransferByClientController(Executor controllersExecutor, ListTransfersByClientUseCase listTransfersByClientUseCase) {
        this.controllersExecutor = controllersExecutor;
        this.listTransfersByClientUseCase = listTransfersByClientUseCase;
    }

    @Operation(summary = "Get client's transfers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Client's Transfers Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListTransfersByClientResponse.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Client Not Exists",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class))}
            )
    })
    @GetMapping
    public CompletableFuture<ResponseEntity<ListTransfersByClientResponse>> listTransfersByClient(@RequestParam String accountNumber) {
        return supplyAsync(() -> listTransfersByClientUseCase.execute(accountNumber), controllersExecutor)
                .thenApply(ResponseEntityTypes::ok);
    }
}
