package com.lawdocgen.lawdocgen.config;

import com.lawdocgen.lawdocgen.entities.Role;
import com.lawdocgen.lawdocgen.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner initializeRoles(RoleRepository roleRepository) {
        return args -> {
            // Vérification si les rôles existent déjà
            if (roleRepository.count() == 0) {
                Role clientRole = new Role(null, "Client", null);
                Role avocatRole = new Role(null, "Avocat", null);
                Role adminRole = new Role(null, "Administrateur", null);

                // Enregistrement des rôles
                roleRepository.saveAll(Arrays.asList(clientRole, avocatRole, adminRole));
                System.out.println("Rôles initialisés : Client, Avocat, Administrateur");
            }
        };
    }
}
