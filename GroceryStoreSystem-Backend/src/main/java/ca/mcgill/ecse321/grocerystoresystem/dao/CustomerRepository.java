package ca.mcgill.ecse321.grocerystoresystem.dao;
<<<<<<< Updated upstream

=======
import java.util.List;
>>>>>>> Stashed changes
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{

	/**
	 * Find Customer based on their ID
	 */
	Customer findCustomerByPersonID(Integer personID);
	
	/**
     * Find Owner based on their first name
     */
    List<Customer> findCustomersByFirstName(String firstName);

    /**
     * Find Owner based on their last name
     */
    List<Customer> findCustomersByLastName(String lastName);
    
    /**
     * Find Owner based on their first and last name
     */
    List<Customer> findCustomersByFirstNameAndLastName(String firstName, String lastName);

	/**
	 * Find Customer by email
	 */
	Customer findCustomerByEmail(String email);
	
    /**
     * Returns true if their is an customer in the database with the passed ID as parameter
     */
    boolean existsByPersonID(Integer personID);
	
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
