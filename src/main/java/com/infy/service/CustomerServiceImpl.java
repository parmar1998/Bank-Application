package com.infy.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.CustomerDTO;
import com.infy.entity.Customer;
import com.infy.exception.CustomerNotFoundException;
import com.infy.repository.CustomerDetailsRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDetailsRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDTO) {
		Customer customer = modelMapper.map(customerDTO, Customer.class);
		Customer savedCustomer = customerRepository.save(customer);
		return modelMapper.map(savedCustomer, CustomerDTO.class);
	}

	@Override
	public CustomerDTO getCustomerById(Long customer_id) throws CustomerNotFoundException {
		Optional<Customer> optionalCustomer = customerRepository.findById(customer_id);
		if (optionalCustomer.isPresent()) {
			return modelMapper.map(optionalCustomer.getClass(), CustomerDTO.class);
		} else {
			throw new CustomerNotFoundException("Customer with ID " + customer_id + " not found");
		}
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return customers.stream().map(cus -> modelMapper.map(cus, CustomerDTO.class)).collect(Collectors.toList());
	}

	@Override
	public CustomerDTO updateCustomer(Long customer_id, CustomerDTO customerDTO) throws CustomerNotFoundException {
		Optional<Customer> optionalCustomer = customerRepository.findById(customer_id);
		if (optionalCustomer.isPresent()) {
			Customer customer = optionalCustomer.get();
			customer.setCustomer_name(customerDTO.getCustomer_name());
			customer.setCustomer_emailId(customerDTO.getCustomer_emailId());
			Customer updatedCustomer = customerRepository.save(customer);
			return modelMapper.map(updatedCustomer, CustomerDTO.class);
		} else {
			throw new CustomerNotFoundException("Customer with id " + customer_id + " doesn't exist");
		}
	}

	@Override
	public void deleteCustomer(Long customer_id) throws CustomerNotFoundException {
		if (!customerRepository.existsById(customer_id)) {
			throw new CustomerNotFoundException("Customer with ID " + customer_id + " not found.");
		}
		customerRepository.deleteById(customer_id);
	}
}
