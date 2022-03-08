package ca.mcgill.ecse321.grocerystoresystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.ItemQuantity;
import ca.mcgill.ecse321.grocerystoresystem.model.Item;
import ca.mcgill.ecse321.grocerystoresystem.model.Order;

public interface ItemQuantityRepository extends CrudRepository<ItemQuantity, Integer>{

	/**
	 * Find ItemQuantity based on their ID
	 */
	ItemQuantity findItemQuantityByQuantityID(Integer quantityID);
	List <ItemQuantity> findItemQuantityByItemNum(Integer itemNum);
	
	/**
	 * Returns true if their is an item quantity in the database with the passed ID as parameter
	 */
	boolean existsByQuantityID(Integer quantityID);
	
}
