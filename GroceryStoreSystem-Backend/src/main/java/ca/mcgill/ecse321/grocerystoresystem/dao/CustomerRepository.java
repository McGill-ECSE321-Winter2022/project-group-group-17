package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{

	/**
	 * Find Customer based on their ID
	 */
	Customer findCustomerByPersonID(Integer personID);


	/**
	 * Returns true if their is an customer in the database with the passed ID as parameter
	 */
	boolean existsByPersonID(Integer personID);
	
}
