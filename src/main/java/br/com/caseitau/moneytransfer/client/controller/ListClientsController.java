package br.com.caseitau.moneytransfer.client.controller;

import br.com.caseitau.moneytransfer.client.dto.FindOneClientResponse;
import br.com.caseitau.moneytransfer.client.dto.ListClientsResponse;
import br.com.caseitau.moneytransfer.client.useCases.ListClientsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping("/client")
public class ListClientsController {
    private final Executor controllersExecutor;
    private final ListClientsUseCase listClientsUseCase;

    public ListClientsController(Executor controllersExecutor, ListClientsUseCase listClientsUseCase) {
        this.controllersExecutor = controllersExecutor;
        this.listClientsUseCase = listClientsUseCase;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<ListClientsResponse>> listClients() {
        return supplyAsync(() -> listClientsUseCase.execute(), controllersExecutor)
                .thenApply(ResponseEntityTypes::ok);
    }
}
