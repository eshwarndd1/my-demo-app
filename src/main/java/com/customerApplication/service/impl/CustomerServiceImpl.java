package com.customerApplication.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.customerApplication.entity.Customer;
import com.customerApplication.exception.CustomerNotFoundException;
import com.customerApplication.model.CustomerRequest;
import com.customerApplication.model.CustomerResponse;
import com.customerApplication.repository.CustomerRepository;
import com.customerApplication.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService{

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerResponse saveCustomer(CustomerRequest request) {
    	logger.info("Saving new customer: {}", request.getEmail());
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setMiddleName(request.getMiddleName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());

        Customer savedCustomer = repository.save(customer);
        logger.info("Customer saved successfully with ID: {}", savedCustomer.getId());
        return mapToResponse(savedCustomer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
    	 logger.info("Fetching all customers");
        return repository.findAll()
                         .stream()
                         .map(this::mapToResponse)
                         .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse getCustomerById(UUID id) {
    	logger.info("Fetching customer with ID: {}", id);
        return repository.findById(id)
                         .map(this::mapToResponse)
                         .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " does not exist"));
    }

    @Override
    public void deleteCustomer(UUID id) {
    	 logger.info("Deleting customer with ID: {}", id);
        if (!repository.existsById(id)) {
            throw new CustomerNotFoundException("Customer with ID " + id + " does not exist");
        }
        repository.deleteById(id);
    }

    @Override
    public CustomerResponse updateCustomer(UUID id, CustomerRequest request) {
    	  logger.info("Updating customer with ID: {}", id);
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " does not exist"));

        // Update the customer details
        customer.setFirstName(request.getFirstName());
        customer.setMiddleName(request.getMiddleName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());

        // Save the updated customer
        Customer updatedCustomer = repository.save(customer);
        logger.info("Customer with ID {} updated successfully", id);
        return mapToResponse(updatedCustomer);
    }


    private CustomerResponse mapToResponse(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        response.setMiddleName(customer.getMiddleName());
        response.setLastName(customer.getLastName());
        response.setEmail(customer.getEmail());
        response.setPhoneNumber(customer.getPhoneNumber());
        return response;
    }
}
