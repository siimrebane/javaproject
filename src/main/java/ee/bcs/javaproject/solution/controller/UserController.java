package ee.bcs.javaproject.solution.controller;

import ee.bcs.javaproject.solution.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("api/user")
    public void createUser(@RequestBody CreateUserRequest request){
        userService.createUser(request);
    }
}
