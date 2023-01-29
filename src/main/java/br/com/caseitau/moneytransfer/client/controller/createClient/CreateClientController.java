package br.com.caseitau.moneytransfer.client.controller.createClient;

import br.com.caseitau.moneytransfer.client.useCases.CreateClientUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping("/v1/client")
public class CreateClientController {
    private final Executor controllerExecutor;
    private final CreateClientUseCase createClientUseCase;

    public CreateClientController(Executor controllerExecutor, CreateClientUseCase createClientUseCase) {
        this.controllerExecutor = controllerExecutor;
        this.createClientUseCase = createClientUseCase;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<CreateClientResponse>> createClient(CreateClientRequest createClientRequest) {
        return null;
    }
}
