 package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer>{
	/**
	 * Find Address based on their ID
	 */
	Address findAddressByAddressID(Integer addressID);
	
	/**
	 * Returns true if their is an address in the database with the passed ID as parameter
	 */
	boolean existsByAddressID(Integer addressID);
}
