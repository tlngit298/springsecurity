package com.udemy.springsecurity.controller;

import com.udemy.springsecurity.model.Customer;
import com.udemy.springsecurity.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody Customer customer) {
        String hashedPwd = passwordEncoder.encode(customer.getPwd());
        customer.setPwd(hashedPwd);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerRepository.save(customer));
    }
}
