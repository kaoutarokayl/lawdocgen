package com.lawdocgen.lawdocgen.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)  // Garantie d'unicité pour le champ name
    private String name; // Client, Avocat, Administrateur

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<User> users;
}