package ee.bcs.javaproject.solution.jdbc.service;

import ee.bcs.javaproject.sample.exception.ApplicationException;
import ee.bcs.javaproject.solution.jdbc.repository.SolutionAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionBankService {

    @Autowired
    private SolutionAccountRepository solutionAccountRepository;

    public void createAccount(String accountNumber) {
        solutionAccountRepository.createAccount(accountNumber);
    }

    public void transferMoney(String fromAccount, String toAccount, Double amount) {
        Double fromAccountCurrentBalance = solutionAccountRepository.getAccountBalance(fromAccount);

        if (amount <= 0) {
            throw new ApplicationException("Sisestatud summa peab olema > 0");
        }

        if (fromAccountCurrentBalance < amount) {
            throw new ApplicationException("Kontol pole piisavalt raha");
        }

        Double toAccountToBalance = solutionAccountRepository.getAccountBalance(toAccount);

        Double fromAccountNewBalance = fromAccountCurrentBalance - amount;
        Double toAccountNewBalance = toAccountToBalance + amount;

        solutionAccountRepository.updateBalance(fromAccount, fromAccountNewBalance);
        solutionAccountRepository.updateBalance(toAccount, toAccountNewBalance);
    }

    public Double getBalance(String accountNumber) {
        return solutionAccountRepository.getAccountBalance(accountNumber);
    }

    public void depositMoney(String accountNumber, Double amount) {
        // validatsioonid
        if (amount <= 0) {
            throw new ApplicationException("Sisestatud summa peab olema > 0");
        }

        Double currentBalance = solutionAccountRepository.getAccountBalance(accountNumber);
        Double newBalance = currentBalance + amount;
        solutionAccountRepository.updateBalance(accountNumber, newBalance);
    }

    public void withdrawMoney(String accountNumber, Double amount) {
        if (amount <= 0) {
            throw new ApplicationException("Sisestatud summa peab olema > 0");
        }

        Double currentBalance = solutionAccountRepository.getAccountBalance(accountNumber);

        if (currentBalance < amount) {
            throw new ApplicationException("Kontol pole piisavalt raha");
        }

        Double newBalance = currentBalance - amount;

        solutionAccountRepository.updateBalance(accountNumber, newBalance);
    }
}
