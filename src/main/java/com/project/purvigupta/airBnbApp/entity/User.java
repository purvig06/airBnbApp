package com.project.purvigupta.airBnbApp.entity;


import com.project.purvigupta.airBnbApp.entity.Enum.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true , nullable = false)
    private String email;


    @Column(nullable = false)
    private String password; //not a plain text

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


}
