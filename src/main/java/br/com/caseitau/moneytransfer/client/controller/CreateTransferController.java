package br.com.caseitau.moneytransfer.client.controller;

import br.com.caseitau.moneytransfer.client.dto.CreateTransferRequest;
import br.com.caseitau.moneytransfer.client.dto.CreateTransferResponse;
import br.com.caseitau.moneytransfer.client.useCases.CreateTransferUseCase;
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
@RequestMapping("/transfer")
public class CreateTransferController {
    private final Executor controllersExecutor;
    private final CreateTransferUseCase createTransferUseCase;

    public CreateTransferController(Executor controllersExecutor, CreateTransferUseCase createTransferUseCase) {
        this.controllersExecutor = controllersExecutor;
        this.createTransferUseCase = createTransferUseCase;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<CreateTransferResponse>> createTransfer(
            @RequestBody @Valid CreateTransferRequest createTransferRequest) {
        return supplyAsync(() -> createTransferUseCase.execute(createTransferRequest), controllersExecutor)
                .thenApply(ResponseEntityTypes::created);
    }
}
