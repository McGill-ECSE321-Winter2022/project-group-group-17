package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {
	
	/**
	 * Find Person based on their ID
	 */
	Person findPersonByPersonID(Integer personID);
	
	/**
	 * Returns true if their is a Person in the database with the passed ID as parameter
	 */
	boolean existsPersonByPersonID(Integer personID);

}
