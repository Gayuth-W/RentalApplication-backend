package com.gayuth.renting_app_backend.model;

import com.gayuth.renting_app_backend.domain.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="seller")
public class Seller {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="seller_id")
    private Long id;

    @Column(name="owner_fname")
    private String fname;
    @Column(name="owner_lname")
    private String lname;
    private String phone;

    private String email;
    @Column(name="sec_key")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="part")
    private UserRole role= UserRole.ROLE_SELLER;
}
