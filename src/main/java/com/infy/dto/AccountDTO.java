package com.infy.dto;

import javax.validation.constraints.NotNull;

import com.infy.entity.Customer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDTO {
	@NotNull(message = "Please provide accountNumber")
	private Long accountNumber;

	@NotNull(message = "Please provide accountType")
	private String accountType;
	private Long balance;
	private Customer customer;
}
