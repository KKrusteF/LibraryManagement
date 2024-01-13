package org.krustef.librarymanagement.service;

import org.krustef.librarymanagement.dto.UserDTO;
import org.krustef.librarymanagement.models.User;
import org.krustef.librarymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsersDTO() {
        return userRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserDTOById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return (user != null) ? mapToDTO(user) : null;
    }

    public UserDTO saveUserDTO(UserDTO userDTO) {
        User user = mapToEntity(userDTO);
        return mapToDTO(userRepository.save(user));
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles()
        );
    }

    private User mapToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles());
        return user;
    }
}
