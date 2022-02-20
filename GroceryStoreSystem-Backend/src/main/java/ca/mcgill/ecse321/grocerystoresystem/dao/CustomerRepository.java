package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{

	Customer findCustomerByEmail(String email);
	
}
