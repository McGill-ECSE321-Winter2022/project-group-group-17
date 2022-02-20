package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.DeliveryOrder;

public interface DeliveryOrderRepository extends CrudRepository<DeliveryOrder, Integer>{

	DeliveryOrder findDeliveryOrderbyOrderID(Integer orderID);
}
