package com.dev.service.boundary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.service.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    // nothing to implement
}