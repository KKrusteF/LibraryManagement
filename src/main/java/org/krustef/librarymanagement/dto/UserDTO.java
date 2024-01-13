package org.krustef.librarymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.krustef.librarymanagement.enums.Role;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private Set<Role> roles;
}
