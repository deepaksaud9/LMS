package com.LMS.dto;

import com.LMS.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private Long id;
    private String address;
    private String name;
    private String email;
    private String contactNo;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

}
