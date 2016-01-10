package com.dev.service.boundary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p.price FROM Product p WHERE p.code = :code")
    double getPrice(@Param("code") String code);
}
