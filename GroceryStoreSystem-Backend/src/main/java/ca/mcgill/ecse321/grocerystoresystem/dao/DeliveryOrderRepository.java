package ca.mcgill.ecse321.grocerystoresystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.DeliveryOrder;
import ca.mcgill.ecse321.grocerystoresystem.model.Address; 
import ca.mcgill.ecse321.grocerystoresystem.model.Person;

public interface DeliveryOrderRepository extends CrudRepository<DeliveryOrder, Integer>{


	List<DeliveryOrder> findByAddress(Address id);
	List<DeliveryOrder> findByPerson(Person email);

}
