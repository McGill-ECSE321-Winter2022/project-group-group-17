package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.InStoreOrder;

public interface InStoreOrderRepository extends CrudRepository<InStoreOrder, Integer>{
	
	InStoreOrder findInOrderStoreByOrderID(Integer orderID);
}
