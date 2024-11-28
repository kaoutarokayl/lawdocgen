package com.lawdocgen.lawdocgen.repositories;

import com.lawdocgen.lawdocgen.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}