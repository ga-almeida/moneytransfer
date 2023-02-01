package br.com.caseitau.moneytransfer.client.controller;

import br.com.caseitau.moneytransfer.client.dto.FindOneClientResponse;
import br.com.caseitau.moneytransfer.client.useCases.FindOneClientUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping("/client/{accountNumber}")
public class FindOneClientController {
    private final Executor controllersExecutor;
    private final FindOneClientUseCase findOneClientUseCase;

    public FindOneClientController(Executor controllersExecutor, FindOneClientUseCase findOneClientUseCase) {
        this.controllersExecutor = controllersExecutor;
        this.findOneClientUseCase = findOneClientUseCase;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<FindOneClientResponse>> findOneClient(@PathVariable String accountNumber) {
        return supplyAsync(() -> findOneClientUseCase.execute(accountNumber), controllersExecutor)
                .thenApply(ResponseEntityTypes::ok);
    }
}
