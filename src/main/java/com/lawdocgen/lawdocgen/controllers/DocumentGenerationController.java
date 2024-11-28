package com.lawdocgen.lawdocgen.controllers;

import com.lawdocgen.lawdocgen.entities.DocumentGeneration;
import com.lawdocgen.lawdocgen.repositories.DocumentGenerationRepository;
import com.lawdocgen.lawdocgen.repositories.DocumentRepository;
import com.lawdocgen.lawdocgen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/document-generations")
public class DocumentGenerationController {

    @Autowired
    private DocumentGenerationRepository documentGenerationRepository;

    @GetMapping(value = "/docgen", produces = { "application/json", "application/xml" })
    public List<DocumentGeneration> getAllDocumentGenerations() {
        return documentGenerationRepository.findAll();
    }

    @PostMapping(value = "/docgen", consumes = { "application/json", "application/xml" }, produces = { "application/json", "application/xml" })
    public DocumentGeneration createDocumentGeneration(@RequestBody DocumentGeneration documentGeneration) {
        return documentGenerationRepository.save(documentGeneration);
    }

    @GetMapping(value = "/docgen/{id}", produces = { "application/json", "application/xml" })
    public ResponseEntity<DocumentGeneration> getDocumentGenerationById(@PathVariable Long id) {
        return documentGenerationRepository.findById(id)
                .map(docGen -> ResponseEntity.ok().body(docGen))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/docgen/{id}", consumes = { "application/json", "application/xml" }, produces = { "application/json", "application/xml" })
    public ResponseEntity<DocumentGeneration> updateDocumentGeneration(@PathVariable Long id, @RequestBody DocumentGeneration documentGenerationDetails) {
        return documentGenerationRepository.findById(id)
                .map(docGen -> {
                    docGen.setStatus(documentGenerationDetails.getStatus());
                    docGen.setUser(documentGenerationDetails.getUser());
                    docGen.setDocument(documentGenerationDetails.getDocument());
                    DocumentGeneration updatedDocGen = documentGenerationRepository.save(docGen);
                    return ResponseEntity.ok().body(updatedDocGen);
                }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/docgen/{id}")
    public ResponseEntity<Void> deleteDocumentGeneration(@PathVariable Long id) {
        return documentGenerationRepository.findById(id)
                .map(docGen -> {
                    documentGenerationRepository.delete(docGen);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}