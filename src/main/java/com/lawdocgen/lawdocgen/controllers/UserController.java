package com.lawdocgen.lawdocgen.controllers;

import com.lawdocgen.lawdocgen.entities.User;
import com.lawdocgen.lawdocgen.repositories.RoleRepository;
import com.lawdocgen.lawdocgen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/users", produces = { "application/json", "application/xml" })
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(value = "/users", consumes = { "application/json", "application/xml" }, produces = { "application/json", "application/xml" })
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping(value = "/users/{id}", produces = { "application/json", "application/xml" })
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping(value = "/users/{id}", consumes = { "application/json", "application/xml" }, produces = { "application/json", "application/xml" })
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDetails.getUsername());
                    user.setPassword(userDetails.getPassword());
                    user.setRole(userDetails.getRole());
                    User updatedUser = userRepository.save(user);
                    return ResponseEntity.ok().body(updatedUser);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/userss/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}