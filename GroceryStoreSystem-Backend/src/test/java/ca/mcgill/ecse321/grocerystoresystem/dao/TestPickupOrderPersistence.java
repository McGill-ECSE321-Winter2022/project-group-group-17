package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

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
}