package ca.mcgill.ecse321.grocerystoresystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.DeliveryOrder;
import ca.mcgill.ecse321.grocerystoresystem.model.InStoreOrder;
import ca.mcgill.ecse321.grocerystoresystem.model.Address; 
import ca.mcgill.ecse321.grocerystoresystem.model.Person;

public interface DeliveryOrderRepository extends CrudRepository<DeliveryOrder, Integer>{
	
	/**
	 * Find DeliveryOrder based on their ID
	 */
	DeliveryOrder findDeliveryOrderByOrderID(Integer orderID);

	/**
	 * Find all delivery orders by personID.
	 */

	List<DeliveryOrder> findDeliveryOrderByPersonPersonID(int personID);
	
	/**
	 * Returns true if their is an delivery order in the database with the passed ID as parameter
	 */
	boolean existsByOrderID(Integer orderID);

}
