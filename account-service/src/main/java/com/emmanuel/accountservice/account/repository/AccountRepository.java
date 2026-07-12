package com.emmanuel.accountservice.account.repository;

import com.emmanuel.accountservice.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query(value = "SELECT nextval('account_number_seq')", nativeQuery = true)
    Long nextAccountNumber();

    boolean existsByCustomerId(UUID id);

    boolean existsByNumber(String number);
}
