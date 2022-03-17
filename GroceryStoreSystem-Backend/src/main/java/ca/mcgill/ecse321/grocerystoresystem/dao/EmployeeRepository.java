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
	
		/**
	 * Find Employee based on their first name
	 */
	List<Employee> findEmployeeByFirstName(String firstName);

	/**
	 * Find Employee based on their last name
	 */
	List<Employee> findEmployeeByLastName(String lastName);


	/**
	 * Find Employee based on their first and last name
	 */
	List<Employee> findEmployeeByFirstNameAndLastName(String firstName, String lastName);

	/**
	 * Returns true if there is an owner in the database with the specified first name
	 */
	boolean existsByFirstName(String firstName);

	/**
	 * Returns true if there is an owner in the database with the specified last name
	 */
	boolean existsByLastName(String lastName);

	/**
	 * Returns true if there is an owner in the database with the specified first name and last name
	 */
	boolean existsByFirstNameAndLastName(String firstName, String lastName);

	/**
	 * Returns true if there is an owner in the database with the specified email
	 */
	boolean existsByEmail(String email);

}
