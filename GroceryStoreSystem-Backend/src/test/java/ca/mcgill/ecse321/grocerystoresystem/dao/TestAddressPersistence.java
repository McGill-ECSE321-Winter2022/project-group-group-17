package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.Customer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestAddressPersistence {

	@Autowired
	private AddressRepository addressRepository;
	
	/**
	 * Cleaning the database after the test
	 */
	@AfterEach
	public void clearDatabases() {
		this.addressRepository.deleteAll();		
	}
	
	/**
	 * Read and Write test for Address Class.
	 */
	@Test
	public void testPersistAndLoadAddress() {
		String streetName = "Huntington Street";
		String streetNum = "123";
		String city = "DrummondVille";
		String postalCode = "H3A1B9";
		String country = "Canada";
		boolean isLocal = true;
		
		Address address = new Address(streetName, streetNum, city, postalCode, country, isLocal);
		this.addressRepository.save(address);
		
		assertTrue(this.addressRepository.existsByAddressID(address.getAddressID()));
		
		Address retrievedAddress = this.addressRepository.findAddressByAddressID(address.getAddressID());
		assertEquals(retrievedAddress.getCity(), address.getCity());
		assertEquals(retrievedAddress.getCountry(), address.getCountry());
	}
}
