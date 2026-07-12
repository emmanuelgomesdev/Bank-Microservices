package com.emmanuel.accountservice.account.service;

import com.emmanuel.accountservice.account.application.command.CommandCreateAccount;
import com.emmanuel.accountservice.account.application.result.AccountBalanceResult;
import com.emmanuel.accountservice.account.application.result.AccountResult;
import com.emmanuel.accountservice.account.domain.Account;
import com.emmanuel.accountservice.account.mapper.ApplicationAccountMapper;
import com.emmanuel.accountservice.account.repository.AccountRepository;
import com.emmanuel.accountservice.account.utils.AccountNumberGenerator;
import com.emmanuel.accountservice.validation.AccountValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository repository;
    private final AccountNumberGenerator accountNumberGenerator;
    private final ApplicationAccountMapper applicationAccountMapper;
    private final AccountValidator accountValidator;

    public AccountService(
            AccountRepository repository,
            AccountNumberGenerator accountNumberGenerator,
            ApplicationAccountMapper applicationAccountMapper,
            AccountValidator accountValidator) {
        this.repository = repository;
        this.accountNumberGenerator = accountNumberGenerator;
        this.applicationAccountMapper = applicationAccountMapper;
        this.accountValidator = accountValidator;
    }

    @Transactional
    public AccountResult create(CommandCreateAccount command) {

        LOGGER.info("Creating account {}", command);

        accountValidator.validateCustomerIdDoesNotExist(command.customerId());

        Long sequence = repository.nextAccountNumber();
        String branch = accountNumberGenerator.defaultBranch();
        String number = accountNumberGenerator.generate(sequence);

        Account account = Account.create(
                command.customerId(),
                branch,
                number
        );

        var saved = repository.save(account);

        return applicationAccountMapper.toResult(saved);
    }

   @Transactional(readOnly = true)
    public AccountResult findById(UUID id) {
        LOGGER.info("Finding account by id {}", id);

        var account = accountValidator.findById(id);
        return applicationAccountMapper.toResult(account);

    }

    @Transactional(readOnly = true)
    public Page<AccountResult> findAll(Pageable pageable) {
        LOGGER.info("Finding all accounts with pageable{}", pageable);

        return repository.findAll(pageable)
                .map(applicationAccountMapper::toResult);
    }

    @Transactional
    public AccountResult block(UUID id){
        LOGGER.info("Blocking account by id {}", id);

        var account = accountValidator.findById(id);
        account.block();
        return applicationAccountMapper.toResult(account);
    }

   @Transactional
    public AccountResult activate(UUID id){
        LOGGER.info("Activating account by id {}", id);

        var account = accountValidator.findById(id);
        account.activate();
        return applicationAccountMapper.toResult(account);
    }

    @Transactional
    public AccountResult close(UUID id){
        LOGGER.info("Closing account by id {}", id);

        var account = accountValidator.findById(id);
        account.close();
        return applicationAccountMapper.toResult(account);
    }

    @Transactional(readOnly = true)
    public AccountBalanceResult getBalance(UUID id){
        LOGGER.info("Getting balance for account {}", id);

        var account = accountValidator.findById(id);
        return applicationAccountMapper.toBalanceResult(account);

    }

    @Transactional(readOnly = true)
    public AccountBalanceResult updateBalance(UUID id, BigDecimal newBalance){
        LOGGER.info("Updating balance for account {}", newBalance);

        var account = accountValidator.findById(id);
        account.updateBalance(newBalance);
        return applicationAccountMapper.toBalanceResult(account);

    }


}
