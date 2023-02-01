package br.com.caseitau.moneytransfer.client.domain.repository;

import br.com.caseitau.moneytransfer.client.domain.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {
    Optional<ClientEntity> findByAccountNumber(String accountNumber);
}
