package ca.mcgill.ecse321.grocerystoresystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.InStoreOrder;
import ca.mcgill.ecse321.grocerystoresystem.model.Person;

public interface InStoreOrderRepository extends CrudRepository<InStoreOrder, Integer>{
	
	/**
	 * Find InStoreOrder based on their ID
	 */
	InStoreOrder findInStoreOrderByOrderID(Integer orderID);
	
	/**
	 * Returns true if their is an in store order in the database with the passed ID as parameter
	 */
	boolean existsByOrderID(Integer orderID);
}
