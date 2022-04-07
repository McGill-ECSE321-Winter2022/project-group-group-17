package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import ca.mcgill.ecse321.grocerystoresystem.model.Customer;
import ca.mcgill.ecse321.grocerystoresystem.model.InStoreOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.PickupOrder;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPickupOrderPersistence {
	@Autowired
	private PickupOrderRepository pickupOrderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Cleaning the database after the test
	 */
	@AfterEach
	public void clearDatabases() {
		this.pickupOrderRepository.deleteAll();
	}
	
	/**
	 * Read and Write test for PickupOrder Class.
	 */
	@Test
	public void testPersistAndLoadDeliveryOrder() {
		int totalCost = 100;
		LocalDateTime orderTime = LocalDateTime.of(2021, 12, 28, 15, 36);
		LocalDateTime pickupTime = LocalDateTime.of(2021, 12, 29, 9, 30);
		boolean isPaid = true;
		
		PickupOrder order = new PickupOrder(totalCost, orderTime, isPaid, pickupTime);
		this.pickupOrderRepository.save(order);
		
		assertTrue(this.pickupOrderRepository.existsByOrderID(order.getOrderID()));
		
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
		LocalDateTime orderTime = LocalDateTime.of(2021, 12, 28, 15, 36);
		LocalDateTime pickupTime = LocalDateTime.of(2021, 12, 29, 9, 30);
		boolean isPaid = true;

		PickupOrder order = new PickupOrder(totalCost, orderTime, isPaid, pickupTime);
		order.setPerson(customer);
		this.pickupOrderRepository.save(order);

		int totalCost2 = 100;
		LocalDateTime orderTime2 = LocalDateTime.of(2021, 12, 28, 15, 36);
		LocalDateTime pickupTime2 = LocalDateTime.of(2021, 12, 29, 9, 30);
		boolean isPaid2 = true;

		PickupOrder order2 = new PickupOrder(totalCost2, orderTime2, isPaid2, pickupTime2);
		order2.setPerson(customer);
		this.pickupOrderRepository.save(order2);

		List<PickupOrder> orders = this.pickupOrderRepository.findPickupOrderByPersonPersonID(customer.getPersonID());
		assertEquals(orders.size(), 2);
	}
}