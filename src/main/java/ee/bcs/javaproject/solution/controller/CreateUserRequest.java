package ee.bcs.javaproject.solution.controller;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String password;
}
