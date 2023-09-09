package com.infy.service;

import java.util.List;

import com.infy.dto.AccountDTO;
import com.infy.exception.AccountNotFoundException;
import com.infy.exception.CustomerNotFoundException;

public interface AccountService {
	public AccountDTO createAccountForCustomer(Long customerId, AccountDTO request) throws CustomerNotFoundException;

	public AccountDTO getAccountDetails(Long accountNumber) throws AccountNotFoundException;

	public List<AccountDTO> getAccountsByCustomerId(Long customerId);

	public AccountDTO updateAccountDetails(Long accountNumber, AccountDTO accountDTO) throws AccountNotFoundException;

	public void deleteAccount(Long accountNumber) throws AccountNotFoundException;
}
