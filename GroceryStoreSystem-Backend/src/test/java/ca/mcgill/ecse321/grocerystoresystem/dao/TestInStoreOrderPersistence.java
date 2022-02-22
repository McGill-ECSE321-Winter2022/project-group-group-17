package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

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
	
	@AfterEach
	public void clearDatabases() {
		this.inOrderStoreRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadInStoreOrder() {
		int totalCost = 100;
		LocalDateTime localDateTime = LocalDateTime.of(2021, 10, 27, 15, 46);
		boolean isPaid = true;
		
		InStoreOrder storeOrder = new InStoreOrder(totalCost, localDateTime, isPaid);
		this.inOrderStoreRepository.save(storeOrder);
		
		assertTrue(this.inOrderStoreRepository.existsByOrderID(storeOrder.getOrderID()));
	}


}
