package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.PickupOrder;

import java.util.List;

public interface PickupOrderRepository extends CrudRepository<PickupOrder, Integer>{

	/**
	 * Find PickupOrder based on their ID
	 */
	PickupOrder findPickupOrderByOrderID(Integer orderID);

	/**
	 * Find all PickupOrders based on the person id.
	 */
	List<PickupOrder> findPickupOrderByPersonPersonID(int personID);

	/**
	 * Returns true if their is a PickupOrder in the database with the passed ID as parameter
	 */
	boolean existsByOrderID(Integer orderID);
}
