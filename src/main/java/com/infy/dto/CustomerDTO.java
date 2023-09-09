package com.infy.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.infy.entity.Account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO {

	@NotNull(message = "{customer.id.notpresent}")
	private Long customer_id;

	@Pattern(regexp = "[A-Za-z]+([A-Za-z]+)*", message = "customer.name.invalid")
	@NotNull(message = "{customer.name.notpresent}")
	private String customer_name;

	@Email(message = "{customer.emailid.invalid}")
	@NotNull(message = "{customer.emailid.notpresent}")
	private String customer_emailId;

	private List<Account> accounts;
}
