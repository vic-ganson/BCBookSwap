package com.hacktheheights.controllers;

import com.hacktheheights.models.Account;
import com.hacktheheights.repositories.AccountRepository;

import org.springframework.web.bind.annotation.*; // for @RestController, @RequestBody, etc.
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class AuthController {

    private final AccountRepository accountRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AccountRepository accountRepo, PasswordEncoder passwordEncoder) {
        this.accountRepo = accountRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Account account) {
        if (accountRepo.findByUsername(account.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username taken");
        }
        account.setPasswordHash(passwordEncoder.encode(account.getPasswordHash()));
        accountRepo.save(account);
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Account account) {
        Optional<Account> userOpt = accountRepo.findByUsername(account.getUsername());
        if (userOpt.isEmpty()) return ResponseEntity.status(401).body("Invalid login");

        Account user = userOpt.get();
        if (!passwordEncoder.matches(account.getPasswordHash(), user.getPasswordHash())) {
            return ResponseEntity.status(401).body("Invalid login");
        }
        return ResponseEntity.ok("Logged in");
    }
}
