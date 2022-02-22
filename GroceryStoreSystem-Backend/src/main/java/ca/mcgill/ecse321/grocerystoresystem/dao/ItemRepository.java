package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Item;

public interface ItemRepository extends CrudRepository<Item, Integer>{

	Item findItemByItemID(Integer id);
	
	boolean existsByItemID(Integer itemID);
}
