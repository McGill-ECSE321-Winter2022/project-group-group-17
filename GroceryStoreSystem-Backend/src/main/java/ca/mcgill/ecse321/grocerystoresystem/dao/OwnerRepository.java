package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Integer>{

	/**
	 * Find Owner based on their ID
	 */
	Owner findOwnerByPersonID(Integer personID);
	
	/**
	 * Returns true if their is an owner in the database with the passed ID as parameter
	 */
	boolean existsByPersonID(Integer personID);
	
}
