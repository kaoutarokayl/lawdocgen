package com.lawdocgen.lawdocgen.repositories;

import com.lawdocgen.lawdocgen.entities.DocumentGeneration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentGenerationRepository extends JpaRepository<DocumentGeneration, Long> {
}