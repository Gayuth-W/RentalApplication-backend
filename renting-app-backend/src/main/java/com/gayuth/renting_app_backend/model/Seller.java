package com.gayuth.renting_app_backend.model;

import com.gayuth.renting_app_backend.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fname;
    private String lname;
    private String phone;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private USER_ROLE role=USER_ROLE.ROLE_SELLER;
}
