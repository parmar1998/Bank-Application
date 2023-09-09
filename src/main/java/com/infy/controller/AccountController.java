package com.infy.controller;

import java.util.List;
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
import com.infy.dto.AccountDTO;
import com.infy.exception.AccountNotFoundException;
import com.infy.exception.CustomerNotFoundException;
import com.infy.service.AccountService;

@RestController
@RequestMapping("/customers/{customer_id}/accounts")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@PostMapping
	public ResponseEntity<AccountDTO> createAccountForCustomer(@PathVariable Long customer_id,
			@RequestBody AccountDTO accountDTO) throws CustomerNotFoundException {

		AccountDTO createdAccount = accountService.createAccountForCustomer(customer_id, accountDTO);
		return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
	}

	@GetMapping("/{accountNumber}")
	public ResponseEntity<AccountDTO> getAccountDetails(@PathVariable Long accountNumber)
			throws AccountNotFoundException {
		AccountDTO account = accountService.getAccountDetails(accountNumber);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<AccountDTO>> getAccountsByCustomerId(@PathVariable Long customer_id) {
		List<AccountDTO> accounts = accountService.getAccountsByCustomerId(customer_id);
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@PutMapping("/{accountNumber}")
	public ResponseEntity<AccountDTO> updateAccountDetails(@PathVariable Long accountNumber,
			@RequestBody AccountDTO accountDTO) throws AccountNotFoundException {
		AccountDTO updatedAccount = accountService.updateAccountDetails(accountNumber, accountDTO);
		return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
	}

	@DeleteMapping("/{accountNumber}")
	public ResponseEntity<Void> deleteAccount(@PathVariable Long accountNumber) throws AccountNotFoundException {
		accountService.deleteAccount(accountNumber);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}