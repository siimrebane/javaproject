package ee.bcs.javaproject.solution.jpa.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
@Entity
@Table(name = "user", schema = "public")
public class UserEntity {
    @Id
    private String username;
    private String password;
}
