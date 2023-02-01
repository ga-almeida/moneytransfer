package br.com.caseitau.moneytransfer.client.controller;

import br.com.caseitau.moneytransfer.client.dto.ListClientsResponse;
import br.com.caseitau.moneytransfer.client.dto.ListTransfersByClientResponse;
import br.com.caseitau.moneytransfer.client.useCases.ListClientsUseCase;
import br.com.caseitau.moneytransfer.client.useCases.ListTransfersByClientUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping("/transfer")
public class ListTransferByClientController {
    private final Executor controllersExecutor;
    private final ListTransfersByClientUseCase listTransfersByClientUseCase;

    public ListTransferByClientController(Executor controllersExecutor, ListTransfersByClientUseCase listTransfersByClientUseCase) {
        this.controllersExecutor = controllersExecutor;
        this.listTransfersByClientUseCase = listTransfersByClientUseCase;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<ListTransfersByClientResponse>> listTransfersByClient(@RequestParam String accountNumber) {
        return supplyAsync(() -> listTransfersByClientUseCase.execute(accountNumber), controllersExecutor)
                .thenApply(ResponseEntityTypes::ok);
    }
}
