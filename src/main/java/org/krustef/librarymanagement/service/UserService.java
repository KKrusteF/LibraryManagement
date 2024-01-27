package org.krustef.librarymanagement.service;

import org.krustef.librarymanagement.models.UserEntity;

public interface UserService {
    void saveUser(UserModel userModel);
    UserEntity findByUsername(String username);
}
