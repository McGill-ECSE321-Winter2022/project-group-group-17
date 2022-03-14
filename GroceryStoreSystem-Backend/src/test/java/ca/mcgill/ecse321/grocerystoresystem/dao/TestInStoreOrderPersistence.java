package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import ca.mcgill.ecse321.grocerystoresystem.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.InStoreOrder;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestInStoreOrderPersistence {
	@Autowired
	private InStoreOrderRepository inOrderStoreRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	/**
	 * Cleaning the database after the test
	 */
	@AfterEach
	public void clearDatabases() {
		this.inOrderStoreRepository.deleteAll();

		this.customerRepository.deleteAll();
	}
	
	/**
	 * Read and Write test for InStoreOrder Class.
	 */
	@Test
	public void testPersistAndLoadInStoreOrder() {
		int totalCost = 100;
		LocalDateTime localDateTime = LocalDateTime.of(2021, 10, 27, 15, 46);
		boolean isPaid = true;
		
		InStoreOrder storeOrder = new InStoreOrder(totalCost, localDateTime, isPaid);
		this.inOrderStoreRepository.save(storeOrder);
		
		assertTrue(this.inOrderStoreRepository.existsByOrderID(storeOrder.getOrderID()));
	}

	@Test
	public void testPersistAndLoadMultipleOrdersPerson() {
		String first_name = "Mario";
		String last_name = "Bouzakhm";

		String email = "mariobouzakhm03@gmail.com";
		String password = "12345678";
		Customer customer = new Customer(first_name, last_name, email, password, false);
		this.customerRepository.save(customer);


		int totalCost = 100;
		LocalDateTime localDateTime = LocalDateTime.of(2021, 10, 27, 15, 46);
		boolean isPaid = true;

		InStoreOrder storeOrder = new InStoreOrder(totalCost, localDateTime, isPaid);
		storeOrder.setPerson(customer);
		this.inOrderStoreRepository.save(storeOrder);

		int totalCost2 = 100;
		LocalDateTime localDateTime2 = LocalDateTime.of(2021, 10, 27, 15, 46);
		boolean isPaid2 = true;

		InStoreOrder storeOrder2 = new InStoreOrder(totalCost2, localDateTime2, isPaid2);
		storeOrder2.setPerson(customer);
		this.inOrderStoreRepository.save(storeOrder2);

		List<InStoreOrder> orders = this.inOrderStoreRepository.findInStoreOrderByPersonPersonID(customer.getPersonID());
		assertEquals(orders.size(), 2);
	}
}
