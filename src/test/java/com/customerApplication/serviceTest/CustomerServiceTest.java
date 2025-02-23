package com.customerApplication.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.customerApplication.entity.Customer;
import com.customerApplication.model.CustomerRequest;
import com.customerApplication.model.CustomerResponse;
import com.customerApplication.repository.CustomerRepository;
import com.customerApplication.service.impl.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class) // Enables Mockito Annotations
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository; // Mock the Repository

    @InjectMocks
    private CustomerServiceImpl customerService; // Inject Mocked Repo into Service

    private Customer customer;
    private CustomerRequest customerRequest;
    private CustomerResponse expectedResponse;
    private UUID customerId;

    @BeforeEach
    void setUp() {
        customerId = UUID.randomUUID();
        
        customer = new Customer();
        customer.setId(customerId);
        customer.setFirstName("John");
        customer.setMiddleName("M");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("+1234567890");

        customerRequest = new CustomerRequest();
        customerRequest.setFirstName("John");
        customerRequest.setMiddleName("M");
        customerRequest.setLastName("Doe");
        customerRequest.setEmail("john.doe@example.com");
        customerRequest.setPhoneNumber("+1234567890");

        expectedResponse = new CustomerResponse();
        expectedResponse.setId(customerId);
        expectedResponse.setFirstName("John");
        expectedResponse.setMiddleName("M");
        expectedResponse.setLastName("Doe");
        expectedResponse.setEmail("john.doe@example.com");
        expectedResponse.setPhoneNumber("+1234567890");
    }

    @Test
    void testSaveCustomer_Success() {
        // Arrange
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // Act
        CustomerResponse response = customerService.saveCustomer(customerRequest);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(customer.getId());
        assertThat(response.getEmail()).isEqualTo(customer.getEmail());

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testGetAllCustomers_Success() {
        // Arrange
        List<Customer> customerList = Stream.of(customer).collect(Collectors.toList());
        when(customerRepository.findAll()).thenReturn(customerList);

        // Act
        List<CustomerResponse> responses = customerService.getAllCustomers();

        // Assert
        assertThat(responses).isNotEmpty();
        assertThat(responses.size()).isEqualTo(1);
        assertThat(responses.get(0).getEmail()).isEqualTo(customer.getEmail());

        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testGetCustomerById_Success() {
        // Arrange
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // Act
        CustomerResponse response = customerService.getCustomerById(customerId);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(customer.getId());
        assertThat(response.getFirstName()).isEqualTo(customer.getFirstName());

        verify(customerRepository, times(1)).findById(customerId);
    }

}
