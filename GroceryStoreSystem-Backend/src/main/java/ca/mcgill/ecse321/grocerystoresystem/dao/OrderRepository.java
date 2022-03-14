package ca.mcgill.ecse321.grocerystoresystem.dao;

import ca.mcgill.ecse321.grocerystoresystem.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    /**
     * Find all the orders by person ID.
     */
    List<Order> findOrderByPersonPersonID(int personID);
}
