package ca.mcgill.ecse321.grocerystoresystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.DeliveryOrder;
import ca.mcgill.ecse321.grocerystoresystem.model.InStoreOrder;
import ca.mcgill.ecse321.grocerystoresystem.model.Address; 
import ca.mcgill.ecse321.grocerystoresystem.model.Person;

public interface DeliveryOrderRepository extends CrudRepository<DeliveryOrder, Integer>{
	
	InStoreOrder findInStoreOrderByOrderID(Integer orderID);
	
	boolean existsByOrderID(Integer orderID);

}
