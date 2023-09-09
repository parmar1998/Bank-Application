package com.infy.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Customer")
@Data
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customer_id;
	private String customer_name;
	private String customer_emailId;
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Account> accounts;
}
