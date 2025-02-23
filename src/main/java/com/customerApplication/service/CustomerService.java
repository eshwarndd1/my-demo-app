package com.customerApplication.service;

import java.util.*;

import com.customerApplication.model.CustomerRequest;
import com.customerApplication.model.CustomerResponse;



public interface CustomerService {
	CustomerResponse saveCustomer(CustomerRequest request);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse getCustomerById(UUID id);
    void deleteCustomer(UUID id);
    CustomerResponse updateCustomer(UUID id, CustomerRequest request);
}
