package com.LMS.dto;

import com.LMS.model.Role;
import com.LMS.model.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String address;
    private String name;
    private String email;
    private String contactNo;
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserResponseDto(User user){
        this.id=user.getId();
        this.address= user.getAddress();
        this.name=user.getName();
        this.email=user.getEmail();
        this.contactNo=user.getContactNo();
        this.role=user.getRole();
    }
}
