package ca.mcgill.ecse321.grocerystoresystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.PickupOrder;
import ca.mcgill.ecse321.grocerystoresystem.model.Person;

public interface PickupOrderRepository extends CrudRepository<PickupOrder, Integer>{

	List<PickupOrder> findPickupOrderByPerson(Person email);
}
