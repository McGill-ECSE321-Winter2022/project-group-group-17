package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Owner;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Integer>{

	/**
	 * Find Owner based on their ID
	 */
	Owner findOwnerByPersonID(Integer personID);

	/**
	 * Find Owner based on their first name
	 */
	List<Owner> findOwnerByFirstName(String firstName);

	/**
	 * Find Owner based on their last name
	 */
	List<Owner> findOwnerByLastName(String lastName);

	/**
	 * Find Owner based on their email address
	 */
	Owner findOwnerByEmail(String email);

	/**
	 * Find Owner based on their first and last name
	 */
	List<Owner> findOwnerByFirstNameAndLastName(String firstName, String lastName);

	/**
	 * Returns true if their is an owner in the database with the specified person ID
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
