package com.infy.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.AccountDTO;
import com.infy.entity.Account;
import com.infy.entity.Customer;
import com.infy.exception.AccountNotFoundException;
import com.infy.exception.CustomerNotFoundException;
import com.infy.repository.AccountDetailsRepository;
import com.infy.repository.CustomerDetailsRepository;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private CustomerDetailsRepository customerRepository;

	@Autowired
	private AccountDetailsRepository accountRepository;

	private final ModelMapper modelMapper;

	@Autowired
	public AccountServiceImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public AccountDTO createAccountForCustomer(Long customerId, AccountDTO request) throws CustomerNotFoundException {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (optionalCustomer.isPresent()) {
			Customer customer = optionalCustomer.get();
			Account account = new Account();
			account.setAccountNumber(request.getAccountNumber());
			account.setAccountType(request.getAccountType());
			account.setBalance(request.getBalance());
			account.setCustomer(customer);

			Account saveAccount = accountRepository.save(account);
			return modelMapper.map(saveAccount, AccountDTO.class);
		} else {
			throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
		}
	}

	@Override
	public AccountDTO getAccountDetails(Long accountNumber) throws AccountNotFoundException {
		Optional<Account> optionalAccount = accountRepository.findById(accountNumber);
		if (optionalAccount.isPresent()) {
			return modelMapper.map(optionalAccount.get(), AccountDTO.class);
		} else {
			throw new AccountNotFoundException("Account with ID " + accountNumber + " not found.");
		}

	}

	@Override
	public List<AccountDTO> getAccountsByCustomerId(Long cutomer_id) {
		List<Account> accounts = accountRepository.findAccountDetailsByCustomerId(cutomer_id);
		return accounts.stream().map(account -> modelMapper.map(accounts, AccountDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public AccountDTO updateAccountDetails(Long accountNumber, AccountDTO accountDTO) throws AccountNotFoundException {
		Optional<Account> optionalAccount = accountRepository.findById(accountNumber);

		if (optionalAccount.isPresent()) {
			Account account = optionalAccount.get();
			account.setAccountType(accountDTO.getAccountType());
			account.setBalance(accountDTO.getBalance());
			Account updatedAccount = accountRepository.save(account);
			return modelMapper.map(updatedAccount, AccountDTO.class);
		} else {
			throw new AccountNotFoundException("Account with ID " + accountNumber + " not found.");
		}
	}

	@Override
	public void deleteAccount(Long accountNumber) throws AccountNotFoundException {
		if (!accountRepository.existsById(accountNumber)) {
			throw new AccountNotFoundException("Account with ID " + accountNumber + " not found.");
		}
		accountRepository.deleteById(accountNumber);
	}
}
