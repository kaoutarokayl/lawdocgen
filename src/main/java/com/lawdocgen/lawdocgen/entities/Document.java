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
public class Document {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;
        private String content;
        private DocumentType type;  // Bail, travail, vente

        @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
        private Set<DocumentGeneration> documentGenerations;
}