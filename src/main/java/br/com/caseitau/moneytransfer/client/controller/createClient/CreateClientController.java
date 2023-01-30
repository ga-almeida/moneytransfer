package br.com.caseitau.moneytransfer.client.controller.createClient;

import br.com.caseitau.moneytransfer.client.controller.ResponseEntityTypes;
import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import br.com.caseitau.moneytransfer.client.dto.CreateClientDTO;
import br.com.caseitau.moneytransfer.client.useCases.CreateClientUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static br.com.caseitau.moneytransfer.client.mapper.CreateClientMapper.requestFromDto;
import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping("/client")
public class CreateClientController {
    private final Executor controllersExecutor;
    private final CreateClientUseCase createClientUseCase;

    public CreateClientController(Executor controllerExecutor, CreateClientUseCase createClientUseCase) {
        this.controllersExecutor = controllerExecutor;
        this.createClientUseCase = createClientUseCase;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<ClientEntity>> createClient(
            @RequestBody @Valid CreateClientRequest createClientRequest) {
        return supplyAsync(() -> createClientUseCase.execute(requestFromDto(createClientRequest)), controllersExecutor)
                .thenApply(ResponseEntityTypes::created);
    }
}
