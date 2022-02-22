package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.PickupOrder;

public interface PickupOrderRepository extends CrudRepository<PickupOrder, Integer>{

	PickupOrder findPickupOrderByOrderID(Integer orderID);
	
	boolean existsByOrderID(Integer orderID);
}
