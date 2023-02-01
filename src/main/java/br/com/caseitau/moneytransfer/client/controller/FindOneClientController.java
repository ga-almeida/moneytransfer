package br.com.caseitau.moneytransfer.client.controller;

import br.com.caseitau.moneytransfer.client.dto.CreateTransferResponse;
import br.com.caseitau.moneytransfer.client.dto.FindOneClientResponse;
import br.com.caseitau.moneytransfer.client.exception.ResponseError;
import br.com.caseitau.moneytransfer.client.useCases.FindOneClientUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@Tag(name = "Clients")
@RequestMapping("/client/{accountNumber}")
public class FindOneClientController {
    private final Executor controllersExecutor;
    private final FindOneClientUseCase findOneClientUseCase;

    public FindOneClientController(Executor controllersExecutor, FindOneClientUseCase findOneClientUseCase) {
        this.controllersExecutor = controllersExecutor;
        this.findOneClientUseCase = findOneClientUseCase;
    }

    @Operation(summary = "Get a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Client Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FindOneClientResponse.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Client not Exists",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class))}
            )
    })
    @GetMapping
    public CompletableFuture<ResponseEntity<FindOneClientResponse>> findOneClient(@PathVariable String accountNumber) {
        return supplyAsync(() -> findOneClientUseCase.execute(accountNumber), controllersExecutor)
                .thenApply(ResponseEntityTypes::ok);
    }
}
