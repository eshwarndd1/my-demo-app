package com.customerApplication.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customerApplication.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID>{

}
