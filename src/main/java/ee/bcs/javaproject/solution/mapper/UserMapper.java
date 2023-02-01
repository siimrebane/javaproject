package ee.bcs.javaproject.solution.mapper;

import ee.bcs.javaproject.solution.controller.CreateUserRequest;
import ee.bcs.javaproject.solution.jpa.user.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity dtoToEntity(CreateUserRequest request);
}
