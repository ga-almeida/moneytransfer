package br.com.caseitau.moneytransfer.client.controller;

import br.com.caseitau.moneytransfer.client.dto.CreateClientRequest;
import br.com.caseitau.moneytransfer.client.dto.CreateClientResponse;
import br.com.caseitau.moneytransfer.client.exception.ResponseError;
import br.com.caseitau.moneytransfer.client.useCases.CreateClientUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@Tag(name = "Clients")
@RequestMapping("/client")
public class CreateClientController {
    private final Executor controllersExecutor;
    private final CreateClientUseCase createClientUseCase;

    public CreateClientController(Executor controllerExecutor, CreateClientUseCase createClientUseCase) {
        this.controllersExecutor = controllerExecutor;
        this.createClientUseCase = createClientUseCase;
    }

    @Operation(summary = "Create a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client Created",
                content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CreateClientResponse.class))}
            ),
            @ApiResponse(responseCode = "409", description = "Client Already Exists",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class))}
            )
    })
    @PostMapping
    public CompletableFuture<ResponseEntity<CreateClientResponse>> createClient(
            @RequestBody @Valid CreateClientRequest createClientRequest) {
        return supplyAsync(() -> createClientUseCase.execute(createClientRequest), controllersExecutor)
                .thenApply(ResponseEntityTypes::created);
    }
}
