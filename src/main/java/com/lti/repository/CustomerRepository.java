package com.lti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	}