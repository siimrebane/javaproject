package ee.bcs.javaproject.solution.jpa;

import ee.bcs.javaproject.solution.jdbc.repository.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountEntityRepository extends JpaRepository<AccountEntity, String> {
    List<AccountEntity> findByLastName(String lastName);
}
