package com.infy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Account")
@Data
@NoArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountNumber;
	private String accountType;
	private Long balance;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonBackReference
	private Customer customer;
}
