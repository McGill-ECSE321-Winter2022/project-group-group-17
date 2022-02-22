package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.DeliveryOrder;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestDeliveryrOrderPersistence {
	@Autowired
	private DeliveryOrderRepository deliveryOrderRepository;
	
	/**
	 * Cleaning the database after the test
	 */
	@AfterEach
	public void clearDatabases() {
		this.deliveryOrderRepository.deleteAll();
	}
	
	/**
	 * Read and Write test for Delivery Order Class.
	 */
	@Test
	public void testPersistAndLoadDeliveryOrder() {
		int totalCost = 100;
		LocalDateTime orderTime = LocalDateTime.of(2021, 12, 28, 15, 36);
		LocalDateTime deliveryTime = LocalDateTime.of(2021, 12, 29, 9, 30);
		boolean isPaid = true;
		
		DeliveryOrder order = new DeliveryOrder(totalCost, orderTime, isPaid, deliveryTime);
		this.deliveryOrderRepository.save(order);
		
		assertTrue(this.deliveryOrderRepository.existsByOrderID(order.getOrderID()));
		
	}
	
	

}
