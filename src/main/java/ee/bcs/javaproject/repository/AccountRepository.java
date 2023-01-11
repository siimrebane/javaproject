package ee.bcs.javaproject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createAccount(String account){
        String sql = "insert into account (account_number) VALUES (:accountNumber)";
        jdbcTemplate.update(sql, Map.of("accountNumber", account));
    }
}
