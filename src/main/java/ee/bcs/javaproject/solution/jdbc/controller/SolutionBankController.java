package ee.bcs.javaproject.solution.jdbc.controller;

import ee.bcs.javaproject.solution.jdbc.service.SolutionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("solution/account")
@RestController
public class SolutionBankController {

    @Autowired
    private SolutionBankService solutionBankService;

    @PostMapping("{accountNumber}")
    public void createAccount(@PathVariable String accountNumber) {
        solutionBankService.createAccount(accountNumber);
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
}
