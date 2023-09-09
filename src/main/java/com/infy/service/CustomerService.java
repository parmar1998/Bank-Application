package com.infy.service;

import java.util.List;

import com.infy.dto.CustomerDTO;
import com.infy.exception.CustomerNotFoundException;

public interface CustomerService {
	public CustomerDTO createCustomer(CustomerDTO customerDTO);

	public CustomerDTO getCustomerById(Long customer_id) throws CustomerNotFoundException;

	public List<CustomerDTO> getAllCustomers();

	public CustomerDTO updateCustomer(Long customer_id, CustomerDTO customerDTO) throws CustomerNotFoundException;

	public void deleteCustomer(Long customer_id) throws CustomerNotFoundException;
}
