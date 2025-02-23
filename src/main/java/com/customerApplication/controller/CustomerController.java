package com.customerApplication.controller;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customerApplication.exception.CustomerNotFoundException;
import com.customerApplication.model.ApiResponseModel;
import com.customerApplication.model.CustomerRequest;
import com.customerApplication.model.CustomerResponse;
import com.customerApplication.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponseModel<CustomerResponse>> createCustomer(@RequestBody CustomerRequest request) {
        CustomerResponse customer = service.saveCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseModel<>(HttpStatus.CREATED, "Customer created successfully", customer));
    }

    @GetMapping
    public ResponseEntity<ApiResponseModel<List<CustomerResponse>>> getAllCustomers() {
        List<CustomerResponse> customers = service.getAllCustomers();
        return ResponseEntity.ok(new ApiResponseModel<>(HttpStatus.OK, "Customers retrieved successfully", customers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseModel<CustomerResponse>> getCustomerById(@PathVariable("id") UUID id) {
        CustomerResponse customer = service.getCustomerById(id);
        return ResponseEntity.ok(new ApiResponseModel<>(HttpStatus.OK, "Customer found", customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseModel<Void>> deleteCustomer(@PathVariable("id") UUID id) {
        service.deleteCustomer(id);
        return ResponseEntity.ok(new ApiResponseModel<>(HttpStatus.OK, "Customer deleted successfully", null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseModel<CustomerResponse>> updateCustomer(
            @PathVariable("id") UUID id,
            @RequestBody CustomerRequest request) {
        CustomerResponse updatedCustomer = service.updateCustomer(id, request);
        return ResponseEntity.ok(new ApiResponseModel<>(HttpStatus.OK, "Customer updated successfully", updatedCustomer));
    }
}
