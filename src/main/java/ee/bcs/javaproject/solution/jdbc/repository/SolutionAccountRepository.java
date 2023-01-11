package ee.bcs.javaproject.solution.jdbc.repository;

import ee.bcs.javaproject.solution.jdbc.controller.StatementRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SolutionAccountRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createAccount(String accountNumber, String firstName, String lastName) {
        String sql = "INSERT INTO account (account_number, first_name, last_name, balance) " +
                     "VALUES (:accountNumber, :firstName, :lastName, 0)";
        Map paramMap = new HashMap();
        paramMap.put("accountNumber", accountNumber);
        paramMap.put("firstName", firstName);
        paramMap.put("lastName", lastName);
        jdbcTemplate.update(sql, paramMap);
    }

    public Account getAccount(String account){
        String sql = "SELECT * FROM account WHERE account_number = :accountNumber";
        Map paramMap = new HashMap();
        paramMap.put("accountNumber", account);
        return jdbcTemplate.queryForObject(sql, paramMap, new BeanPropertyRowMapper<>(Account.class));
    }

    public void updateBalance(String accountNumber, Double balance){
        String sql3 = "UPDATE account set balance = :balance WHERE account_number = :accountNumber";
        Map paramMap3 = new HashMap();
        paramMap3.put("accountNumber", accountNumber);
        paramMap3.put("balance", balance);
        jdbcTemplate.update(sql3, paramMap3);
    }

    public void lockAccount(String accountNumber) {
        String sql = "UPDATE account SET locked = true WHERE account_number = :accountNumber";
        Map paramMap = new HashMap();
        paramMap.put("accountNumber", accountNumber);
        jdbcTemplate.update(sql, paramMap);
    }

    public void unlockAccount(String accountNumber) {
        String sql = "UPDATE account SET locked = false WHERE account_number = :accountNumber";
        Map paramMap = new HashMap();
        paramMap.put("accountNumber", accountNumber);
        jdbcTemplate.update(sql, paramMap);
    }

    public void addStatementRow(String fromAccount, String toAccount, Double amount){
        String sql = "INSERT INTO account_statement (from_account, to_account, amount) " +
                     "VALUES (:fromAccount, :toAccount, :amount)";
        Map paramMap = new HashMap();
        paramMap.put("fromAccount", fromAccount);
        paramMap.put("toAccount", toAccount);
        paramMap.put("amount", amount);
        jdbcTemplate.update(sql, paramMap);

    }

    public List<StatementRow> getAccountStatement(String accountNumber) {
        String sql = "SELECT * FROM account_statement WHERE from_account = :accountNumber OR to_account = :accountNumber";
        Map paramMap = new HashMap();
        paramMap.put("accountNumber", accountNumber);
        return jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(StatementRow.class));
    }
}
