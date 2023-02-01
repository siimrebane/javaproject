package ee.bcs.javaproject.solution.service;

import ee.bcs.javaproject.solution.controller.CreateUserRequest;
import ee.bcs.javaproject.solution.jpa.user.UserEntity;
import ee.bcs.javaproject.solution.jpa.user.UserRepository;
import ee.bcs.javaproject.solution.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void createUser(CreateUserRequest request) {
        userRepository.save(userMapper.dtoToEntity(request));
    }
}
