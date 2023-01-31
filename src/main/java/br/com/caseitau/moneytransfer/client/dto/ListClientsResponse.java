package br.com.caseitau.moneytransfer.client.dto;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;

import java.util.List;

public class ListClientsResponse {
    private List<ClientEntity> clients;

    private ListClientsResponse(List<ClientEntity> clients) {
        this.clients = clients;
    }

    public List<ClientEntity> getClients() {
        return clients;
    }

    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder {
        private List<ClientEntity> clients;

        private Builder() {
        }

        public Builder clients(List<ClientEntity> clients) {
            this.clients = clients;
            return this;
        }

        public ListClientsResponse build() {
            return new ListClientsResponse(clients);
        }
    }
}
