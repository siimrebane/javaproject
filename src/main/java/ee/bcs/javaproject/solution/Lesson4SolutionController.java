package ee.bcs.javaproject.solution;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("solution/account")
@RestController
public class Lesson4SolutionController {

    Map<String, Double> accounts = new HashMap<>();

    @PostMapping("{accountNumber}")
    public void createAccount(@PathVariable String accountNumber) {
        accounts.put(accountNumber, 0.0);
    }

    @GetMapping("{accountNumber}")
    public Double getAccount(@PathVariable String accountNumber) {
        return accounts.get(accountNumber);
    }

    @PutMapping("deposit/{accountNumber}/{amount}")
    public String depositMoney(@PathVariable String accountNumber, @PathVariable Double amount) {
        // validatsioonid
        if (amount <= 0) {
            return "Sisestatud summa peab olema > 0";
        }

        // mida meil on vaja teha
        Double currentBalance = accounts.get(accountNumber);
        Double newBalance = currentBalance + amount;
        accounts.put(accountNumber, newBalance);
        return "Raha kontole kantud";
    }

    @PutMapping("withdraw/{accountNumber}/{amount}")
    public String withdrawMoney(@PathVariable String accountNumber, @PathVariable Double amount) {
        if (amount <= 0) {
            return "Sisestatud summa peab olema > 0";
        }

        Double currentBalance = accounts.get(accountNumber);
        if (currentBalance < amount) {
            return "Kontol pole piisavalt raha";
        }

        Double newBalance = currentBalance - amount;
        accounts.put(accountNumber, newBalance);
        return "Tehing tehtud";
    }

    @PutMapping("transfer/{fromAccount}/{toAccount}/{amount}")
    public String transferMoney(@PathVariable String fromAccount, @PathVariable String toAccount, @PathVariable Double amount) {
        if (amount <= 0) {
            return "Sisestatud summa peab olema > 0";
        }
        Double fromAccountCurrentBalance = accounts.get(fromAccount);
        if (fromAccountCurrentBalance < amount) {
            return "Kontol pole piisavalt raha";
        }
        Double toAccountToBalance = accounts.get(toAccount);
        Double fromAccountNewBalance = fromAccountCurrentBalance - amount;
        Double toAccountNewBalance = toAccountToBalance + amount;
        accounts.put(fromAccount, fromAccountNewBalance);
        accounts.put(toAccount, toAccountNewBalance);
        return "Tehing tehtud";
    }
}
