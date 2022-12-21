package ee.bcs.javaproject.solution.jdbc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SolutionAccountRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createAccount(String accountNumber) {
        String sql = "INSERT INTO account (account_number, balance) VALUES (:accountNumber, 0)";
        Map paramMap = new HashMap();
        paramMap.put("accountNumber", accountNumber);
        jdbcTemplate.update(sql, paramMap);
    }

    public Double getAccountBalance(String account){
        String sql = "SELECT balance FROM account WHERE account_number = :accountNumber";
        Map paramMap = new HashMap();
        paramMap.put("accountNumber", account);
        return jdbcTemplate.queryForObject(sql, paramMap, Double.class);
    }

    public void updateBalance(String accountNumber, Double balance){
        String sql3 = "UPDATE account set balance = :balance WHERE account_number = :accountNumber";
        Map paramMap3 = new HashMap();
        paramMap3.put("accountNumber", accountNumber);
        paramMap3.put("balance", balance);
        jdbcTemplate.update(sql3, paramMap3);
    }
}
