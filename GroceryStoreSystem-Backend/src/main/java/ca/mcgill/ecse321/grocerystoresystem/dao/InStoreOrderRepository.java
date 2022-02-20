package ca.mcgill.ecse321.grocerystoresystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.InStoreOrder;
import ca.mcgill.ecse321.grocerystoresystem.model.Person;

public interface InStoreOrderRepository extends CrudRepository<InStoreOrder, Integer>{
	
	List<InStoreOrder> findInStoreOrderByPerson(Person email);
}
