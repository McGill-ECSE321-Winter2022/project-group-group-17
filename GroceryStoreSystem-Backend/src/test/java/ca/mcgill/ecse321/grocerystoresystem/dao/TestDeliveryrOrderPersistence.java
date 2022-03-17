package ca.mcgill.ecse321.grocerystoresystem.dao;

import java.time.LocalDateTime;
import java.util.List;

import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.Customer;
import ca.mcgill.ecse321.grocerystoresystem.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.DeliveryOrder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestDeliveryrOrderPersistence {
	@Autowired
	private DeliveryOrderRepository deliveryOrderRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Cleaning the database after the test
	 */
	@AfterEach
	public void clearDatabases() {
		this.deliveryOrderRepository.deleteAll();

		this.customerRepository.deleteAll();

		this.addressRepository.deleteAll();
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

	@Test
	public void testPersistAndLoadDeliveryOrderAddress() {
		String streetName = "Huntington Street";
		String streetNum = "123";
		String city = "DrummondVille";
		String postalCode = "H3A1B9";
		String country = "Canada";
		boolean isLocal = true;

		Address address = new Address(streetName, streetNum, city, postalCode, country, isLocal);

		this.addressRepository.save(address);

		int totalCost = 100;
		LocalDateTime orderTime = LocalDateTime.of(2021, 12, 28, 15, 36);
		LocalDateTime deliveryTime = LocalDateTime.of(2021, 12, 29, 9, 30);
		boolean isPaid = true;

		DeliveryOrder order = new DeliveryOrder(totalCost, orderTime, isPaid, deliveryTime, address);
		this.deliveryOrderRepository.save(order);

		DeliveryOrder retrievedOrder = this.deliveryOrderRepository.findDeliveryOrderByOrderID(order.getOrderID());
		assertNotNull(retrievedOrder);
		assertEquals(retrievedOrder.getAddress().getAddressID(), address.getAddressID());
		assertEquals(retrievedOrder.getAddress().getCity(), address.getCity());
		assertEquals(retrievedOrder.getAddress().getCountry(), address.getCountry());
	}

	@Test
	public void testDeliveryOrderPersistAndLoadPerson() {
		int totalCost = 100;
		LocalDateTime orderTime = LocalDateTime.of(2021, 12, 28, 15, 36);
		LocalDateTime deliveryTime = LocalDateTime.of(2021, 12, 29, 9, 30);
		boolean isPaid = true;

		DeliveryOrder order = new DeliveryOrder(totalCost, orderTime, isPaid, deliveryTime);

		String first_name = "Mario";
		String last_name = "Bouzakhm";

		String email = "mariobouzakhm03@gmail.com";
		String password = "12345678";
		Customer customer = new Customer(first_name, last_name, email, password, false);
		this.customerRepository.save(customer);

		order.setPerson(customer);
		this.deliveryOrderRepository.save(order);

		DeliveryOrder retrievedOrder = this.deliveryOrderRepository.findDeliveryOrderByOrderID(order.getOrderID());
		assertNotNull(retrievedOrder);

		Person retrievedOrderPerson = retrievedOrder.getPerson();
		assertNotNull(retrievedOrderPerson);
		assertNotNull(customer.getFirstName(), retrievedOrderPerson.getFirstName());
	}

	@Test
	public void testPersistAndLoadMultipleOrdersForAddress() {
		String streetName = "Huntington Street";
		String streetNum = "123";
		String city = "DrummondVille";
		String postalCode = "H3A1B9";
		String country = "Canada";
		boolean isLocal = true;

		Address address = new Address(streetName, streetNum, city, postalCode, country, isLocal);

		this.addressRepository.save(address);

		int totalCost = 100;
		LocalDateTime orderTime = LocalDateTime.of(2021, 12, 28, 15, 36);
		LocalDateTime deliveryTime = LocalDateTime.of(2021, 12, 29, 9, 30);
		boolean isPaid = true;

		DeliveryOrder order = new DeliveryOrder(totalCost, orderTime, isPaid, deliveryTime, address);
		this.deliveryOrderRepository.save(order);

		int totalCost2 = 1000;
		LocalDateTime orderTime2 = LocalDateTime.of(2021, 11, 28, 15, 36);
		LocalDateTime deliveryTime2 = LocalDateTime.of(2021, 11, 29, 9, 30);
		boolean isPaid2 = false;

		DeliveryOrder order2 = new DeliveryOrder(totalCost2, orderTime2, isPaid2, deliveryTime2, address);
		this.deliveryOrderRepository.save(order2);

		List<DeliveryOrder> orders = this.deliveryOrderRepository.findDeliveryOrderByDeliveryAddressAddressID(address.getAddressID());
		assertEquals(orders.size(), 2);
	}
}
