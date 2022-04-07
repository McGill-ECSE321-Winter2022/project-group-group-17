package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {
	
	/**
	 * Find Person based on their ID
	 */
	Person findPersonByPersonID(Integer personID);

	/**
	 * Find Person based on Email
	 */
	List<Person> findPersonByEmail(String email);

	/**
	 * Returns true if their is a Person in the database with the passed ID as parameter
	 */
	boolean existsPersonByPersonID(Integer personID);

	/**
	 * Returns true if there is a Person in the database with the specified email
	 */
	boolean existsPersonByEmail(String email);

}
