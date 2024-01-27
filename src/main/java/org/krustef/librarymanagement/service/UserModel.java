package org.krustef.librarymanagement.service;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserModel {
    private Long id;
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
