package ee.bcs.javaproject.solution.jdbc.controller;

import ee.bcs.javaproject.solution.jdbc.repository.Account;
import ee.bcs.javaproject.solution.jdbc.service.SolutionBankService;
import ee.bcs.javaproject.solution.jpa.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("solution/account")
@RestController
public class SolutionBankController {

    @Autowired
    private SolutionBankService solutionBankService;

    @PostMapping("")
    public void createAccount(@RequestBody CreateAccountRequest request) {
        solutionBankService.createAccount(request);
    }

    @GetMapping("{accountNumber}")
    public Double getAccount(@PathVariable String accountNumber) {
        return solutionBankService.getBalance(accountNumber);
    }

    @PutMapping("deposit/{accountNumber}/{amount}")
    public void depositMoney(@PathVariable String accountNumber, @PathVariable Double amount) {
        solutionBankService.depositMoney(accountNumber, amount);
    }

    @PutMapping("withdraw/{accountNumber}/{amount}")
    public void withdrawMoney(@PathVariable String accountNumber, @PathVariable Double amount) {
        solutionBankService.withdrawMoney(accountNumber, amount);
    }

    @PutMapping("transfer/{fromAccount}/{toAccount}/{amount}")
    public void transferMoney(@PathVariable String fromAccount, @PathVariable String toAccount, @PathVariable Double amount) {
        solutionBankService.transferMoney(fromAccount, toAccount, amount);
    }

    @PutMapping("{accountNumber}/lock")
    public void lockAccount(@PathVariable String accountNumber){
        solutionBankService.lockAccount(accountNumber);
    }

    @PutMapping("{accountNumber}/unlock")
    public void unlockAccount(@PathVariable String accountNumber){
        solutionBankService.unlockAccount(accountNumber);
    }

    @GetMapping("{accountNumber}/statement")
    public List<StatementRow> getAccountStatement(@PathVariable String accountNumber){
        return solutionBankService.getStatement(accountNumber);
    }

    @GetMapping
    public List<Account> searchAccounts(String lastName){
        return solutionBankService.searchAccounts(lastName);
    }
}

