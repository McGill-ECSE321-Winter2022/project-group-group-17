package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.Customer;
import ca.mcgill.ecse321.grocerystoresystem.model.Employee;
import ca.mcgill.ecse321.grocerystoresystem.model.EmployeeStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestGroceryStoreSystemPersistence {
	
	@Autowired
	private DeliveryOrderRepository deliveryRepository;
	
	@Autowired
	private InStoreOrderRepository inOrderStoreRepository;
	
	@Autowired
	private ItemQuantityRepository itemRepository;

	
	@Autowired
	private PickupOrderRepository pickupOrderRepository;
	
	@AfterEach
	public void clearDatabases() {
		
	}
}
