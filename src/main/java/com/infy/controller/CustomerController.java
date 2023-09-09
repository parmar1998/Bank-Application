package com.infy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.infy.dto.CustomerDTO;
import com.infy.exception.CustomerNotFoundException;
import com.infy.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
		CustomerDTO createCustomer = customerService.createCustomer(customerDTO);
		return new ResponseEntity<CustomerDTO>(createCustomer, HttpStatus.OK);
	}

	@GetMapping("/{customer_id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long customer_id)
			throws CustomerNotFoundException {
		CustomerDTO getCustomer = customerService.getCustomerById(customer_id);
		return new ResponseEntity<>(getCustomer, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
		List<CustomerDTO> customers = customerService.getAllCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	@PutMapping("/{customer_id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long customer_id,
			@RequestBody CustomerDTO customerDTO) throws CustomerNotFoundException {

		CustomerDTO updatedCustomer = customerService.updateCustomer(customer_id, customerDTO);
		return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
	}

	@DeleteMapping("/{customer_id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long customer_id) throws CustomerNotFoundException {
		customerService.deleteCustomer(customer_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}