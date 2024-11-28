package com.lawdocgen.lawdocgen.controllers;

import com.lawdocgen.lawdocgen.entities.Document;
import com.lawdocgen.lawdocgen.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    @GetMapping(value = "/documents", produces = { "application/json", "application/xml" })
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @PostMapping(value = "/documents", consumes = { "application/json", "application/xml" }, produces = { "application/json", "application/xml" })
    public Document createDocument(@RequestBody Document document) {
        return documentRepository.save(document);
    }

    @GetMapping(value = "/documents/{id}", produces = { "application/json", "application/xml" })
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
        return documentRepository.findById(id)
                .map(doc -> ResponseEntity.ok().body(doc))
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping(value = "/documents/{id}", consumes = { "application/json", "application/xml" }, produces = { "application/json", "application/xml" })
    public ResponseEntity<Document> updateDocument(@PathVariable Long id, @RequestBody Document documentDetails) {
        return documentRepository.findById(id)
                .map(document -> {
                    document.setTitle(documentDetails.getTitle());
                    document.setContent(documentDetails.getContent());
                    document.setType(documentDetails.getType());
                    Document updatedDocument = documentRepository.save(document);
                    return ResponseEntity.ok().body(updatedDocument);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/documents/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        return documentRepository.findById(id)
                .map(doc -> {
                    documentRepository.delete(doc);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}