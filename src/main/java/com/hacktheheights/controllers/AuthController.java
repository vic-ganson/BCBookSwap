package com.hacktheheights.controllers;

import com.hacktheheights.models.Account;
import com.hacktheheights.repositories.AccountRepository;
import org.springframework.web.bind.annotation.*;
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
        if (account.getEmail() == null || account.getPassword() == null) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }

        if (accountRepo.findByEmail(account.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepo.save(account);

        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Account account) {
        if (account.getEmail() == null || account.getPassword() == null) {
            return ResponseEntity.status(400).body("Email and password are required");
        }

        Optional<Account> userOpt = accountRepo.findByEmail(account.getEmail());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid login");
        }

        Account user = userOpt.get();
        if (!passwordEncoder.matches(account.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid login");
        }

        return ResponseEntity.ok("Logged in");
    }
}
