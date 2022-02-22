package ca.mcgill.ecse321.grocerystoresystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.ItemQuantity;
import ca.mcgill.ecse321.grocerystoresystem.model.Item;
import ca.mcgill.ecse321.grocerystoresystem.model.Order;

public interface ItemQuantityRepository extends CrudRepository<ItemQuantity, Integer>{

	ItemQuantity findItemQuantityByQuantityID(Integer quantityID);
	
	boolean existsByQuantityID(Integer quantityID);
	
}
