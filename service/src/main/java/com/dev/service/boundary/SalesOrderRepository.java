package com.dev.service.boundary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.service.entity.Customer;
import com.dev.service.entity.SalesOrder;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, String> {

    List<SalesOrder> findByCustomer(Customer customer);
}