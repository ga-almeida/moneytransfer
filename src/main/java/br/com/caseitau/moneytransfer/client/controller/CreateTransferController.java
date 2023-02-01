package br.com.caseitau.moneytransfer.client.controller;

import br.com.caseitau.moneytransfer.client.dto.CreateTransferRequest;
import br.com.caseitau.moneytransfer.client.dto.CreateTransferResponse;
import br.com.caseitau.moneytransfer.client.exception.ResponseError;
import br.com.caseitau.moneytransfer.client.useCases.CreateTransferUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Transfer")
@RequestMapping("/transfer")
public class CreateTransferController {
    private final Executor controllersExecutor;
    private final CreateTransferUseCase createTransferUseCase;

    public CreateTransferController(Executor controllersExecutor, CreateTransferUseCase createTransferUseCase) {
        this.controllersExecutor = controllersExecutor;
        this.createTransferUseCase = createTransferUseCase;
    }

    @Operation(summary = "Create a transfer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transfer Created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateTransferResponse.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Value Transfer Higher Balance Client Origin",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Client Origin And From Not Exists",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class))}
            )
    })
    @PostMapping
    public CompletableFuture<ResponseEntity<CreateTransferResponse>> createTransfer(
            @RequestBody @Valid CreateTransferRequest createTransferRequest) {
        return supplyAsync(() -> createTransferUseCase.execute(createTransferRequest), controllersExecutor)
                .thenApply(ResponseEntityTypes::created);
    }
}
