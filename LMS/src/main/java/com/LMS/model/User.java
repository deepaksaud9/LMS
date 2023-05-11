package com.LMS.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    private String contactNo;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean presentStatus = true;
    @Enumerated(EnumType.STRING)
    private UserEnum status;


}
