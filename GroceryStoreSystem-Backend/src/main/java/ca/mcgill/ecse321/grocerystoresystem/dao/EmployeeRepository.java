package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{


	/**
	 * Find Employee based on their ID
	 */	
	Employee findEmployeeByEmail(String email);
  
	/**
	 * Find Employee based on their ID
	 */	
	Employee findEmployeeByPersonID(Integer personID);
	
	/**
	 * Returns true if there is an employee in the database with the passed ID as parameter
	 */
	boolean existsByPersonID(Integer personID);
	
}
