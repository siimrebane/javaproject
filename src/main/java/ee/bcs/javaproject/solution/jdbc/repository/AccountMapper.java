package ee.bcs.javaproject.solution.jdbc.repository;

import ee.bcs.javaproject.solution.jpa.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target="name", expression = "java(entity.getFirstName() + \" \" + entity.getLastName() )")
    Account entityToDto(AccountEntity entity);

    List<Account> entityToDto(List<AccountEntity> accounts);
}
