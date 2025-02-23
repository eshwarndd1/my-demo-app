package com.customerApplication.model;

import java.util.UUID;

import lombok.Data;
@Data
public class CustomerResponse {
	private UUID id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
