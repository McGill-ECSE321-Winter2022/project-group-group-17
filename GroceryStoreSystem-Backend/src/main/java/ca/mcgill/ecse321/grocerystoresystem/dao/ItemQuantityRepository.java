package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.ItemQuantity;

public interface ItemQuantityRepository extends CrudRepository<ItemQuantity, Integer>{

	ItemQuantity findItemQuantityByquantityID(Integer quantityID);
}
