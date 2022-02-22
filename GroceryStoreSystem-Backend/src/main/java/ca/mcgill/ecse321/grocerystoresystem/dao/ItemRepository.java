package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Item;

public interface ItemRepository extends CrudRepository<Item, Integer>{

	/**
	 * Find ItemRepository based on their ID
	 */
	Item findItemByItemID(Integer id);
	
	/**
	 * Returns true if their is an item in the database with the passed ID as parameter
	 */
	boolean existsByItemID(Integer itemID);
}
