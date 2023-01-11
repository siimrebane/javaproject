package ee.bcs.javaproject.solution.jdbc.service;

import ee.bcs.javaproject.sample.exception.ApplicationException;
import ee.bcs.javaproject.solution.jdbc.controller.CreateAccountRequest;
import ee.bcs.javaproject.solution.jdbc.controller.StatementRow;
import ee.bcs.javaproject.solution.jdbc.repository.Account;
import ee.bcs.javaproject.solution.jdbc.repository.AccountMapper;
import ee.bcs.javaproject.solution.jdbc.repository.SolutionAccountRepository;
import ee.bcs.javaproject.solution.jpa.AccountEntity;
import ee.bcs.javaproject.solution.jpa.AccountEntityRepository;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SolutionBankService {

    @Autowired
    private SolutionAccountRepository solutionAccountRepository;

    @Autowired
    private AccountEntityRepository accountEntityRepository;

    @Autowired
    private AccountMapper accountMapper;

    public void createAccount(CreateAccountRequest request) {
        AccountEntity account = new AccountEntity();
        account.setAccountNumber(request.getAccountNumber());
        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
        account.setBalance(0.0);
        accountEntityRepository.save(account);
    }

    public void transferMoney(String fromAccountNumber, String toAccountNumber, Double amount) {
        AccountEntity fromAccountEntity = accountEntityRepository.findById(fromAccountNumber).orElseThrow();
        // Account fromAccount = solutionAccountRepository.getAccount(fromAccountNumber);
        Account toAccount = solutionAccountRepository.getAccount(toAccountNumber);

        validateTransferVariables(amount, fromAccountEntity, toAccount);

        Double fromAccountNewBalance = fromAccountEntity.getBalance() - amount;
        Double toAccountNewBalance = toAccount.getBalance() + amount;

        solutionAccountRepository.addStatementRow(fromAccountNumber, toAccountNumber, amount);

        fromAccountEntity.setBalance(fromAccountNewBalance);
        solutionAccountRepository.updateBalance(toAccountNumber, toAccountNewBalance);
    }

    public Double getBalance(String accountNumber) {
        return solutionAccountRepository.getAccount(accountNumber).getBalance();
    }

    public void depositMoney(String accountNumber, Double amount) {
        validateAmountInput(amount);

        solutionAccountRepository.addStatementRow(null, accountNumber, amount);
        Double currentBalance = solutionAccountRepository.getAccount(accountNumber).getBalance();
        Double newBalance = currentBalance + amount;
        solutionAccountRepository.updateBalance(accountNumber, newBalance);
    }

    public void withdrawMoney(String accountNumber, Double amount) {
        Double currentBalance = solutionAccountRepository.getAccount(accountNumber).getBalance();

        validateWithdraw(amount, currentBalance);

        Double newBalance = currentBalance - amount;

        solutionAccountRepository.addStatementRow(accountNumber, null, amount);
        solutionAccountRepository.updateBalance(accountNumber, newBalance);
    }

    private static void validateAmountInput(Double amount) {
        if (amount <= 0) {
            throw new ApplicationException("Sisestatud summa peab olema > 0");
        }
    }

    private static void validateWithdraw(Double amount, Double fromAccountCurrentBalance) {
        validateAmountInput(amount);

        if (fromAccountCurrentBalance < amount) {
            throw new ApplicationException("Kontol pole piisavalt raha");
        }
    }

    private static void validateTransferVariables(Double amount, AccountEntity fromAccount, Account toAccount) {
        validateAmountInput(amount);

        if (fromAccount.getBalance() < amount) {
            throw new ApplicationException("Kontol pole piisavalt raha");
        }
        if(fromAccount.isLocked()){
            throw new ApplicationException("From account is locked");
        }
        if (toAccount.isLocked()) {
            throw new ApplicationException("To account is locked");
        }

    }

    public void lockAccount(String accountNumber) {
        solutionAccountRepository.lockAccount(accountNumber);
    }

    public void unlockAccount(String accountNumber) {
        solutionAccountRepository.unlockAccount(accountNumber);
    }

    public List<StatementRow> getStatement(String accountNumber) {
        return solutionAccountRepository.getAccountStatement(accountNumber);
    }

    public List<Account> searchAccounts(String lastName) {
        var accounts = accountEntityRepository.findByLastName(lastName);
        return accountMapper.entityToDto(accounts);
    }
}
