package com.infy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.infy.entity.Account;

public interface AccountDetailsRepository extends JpaRepository<Account, Long> {
	@Query("SELECT a FROM Account a WHERE a.customer.customer_id = :customer_id")
	List<Account> findAccountDetailsByCustomerId(@Param("customer_id") Long customer_id);
}
