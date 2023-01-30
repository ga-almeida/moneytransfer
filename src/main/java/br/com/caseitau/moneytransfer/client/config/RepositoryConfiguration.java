package br.com.caseitau.moneytransfer.client.config;

import br.com.caseitau.moneytransfer.client.domain.repository.ClientRepositoryInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public ClientRepositoryInMemory clientRepositoryInMemory() {
        return new ClientRepositoryInMemory();
    }
}
