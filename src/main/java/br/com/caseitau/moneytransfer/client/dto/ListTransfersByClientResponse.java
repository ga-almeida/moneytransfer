package br.com.caseitau.moneytransfer.client.dto;

import java.util.List;

public class ListTransfersByClientResponse {
    private String name;
    private String accountNumber;
    private List<TransferByClientDTO> transfers;

    private ListTransfersByClientResponse(String name, String accountNumber, List<TransferByClientDTO> transfers) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.transfers = transfers;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<TransferByClientDTO> getTransfers() {
        return transfers;
    }


    public static final class Builder {
        private String name;
        private String accountNumber;
        private List<TransferByClientDTO> transfers;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder transfers(List<TransferByClientDTO> transfers) {
            this.transfers = transfers;
            return this;
        }

        public ListTransfersByClientResponse build() {
            return new ListTransfersByClientResponse(name, accountNumber, transfers);
        }
    }
}
