package com.customerApplication.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class CustomerRequest {
	@NotBlank
    private String firstName;
    
    private String middleName;

    @NotBlank
    private String lastName;
    
    @Email
    @NotBlank
    private String email;
    
    @NotBlank
    private String phoneNumber;
}
