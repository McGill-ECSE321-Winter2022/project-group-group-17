package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.StoreHour;
import ca.mcgill.ecse321.grocerystoresystem.model.Weekdays;

public interface StoreHourRepository extends CrudRepository<StoreHour, Integer >{

	/**
	 * Find StoreHour based on their ID
	 */
	StoreHour findStoreHourByStoreHourID(Integer storeHourID);
	
	/**
	 * Returns true if their is a Store Hour in the database with the passed ID as parameter
	 */
	boolean existsByStoreHourID(Integer storeHourID);

}
