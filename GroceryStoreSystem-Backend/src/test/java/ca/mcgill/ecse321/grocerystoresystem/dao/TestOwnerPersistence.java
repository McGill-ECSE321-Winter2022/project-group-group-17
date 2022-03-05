package ca.mcgill.ecse321.grocerystoresystem.dao;

import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.Owner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestOwnerPersistence {
	
	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private AddressRepository addressRepository;

	/**
	 * Cleaning the database after the test
	 */
	@AfterEach
	public void clearDatabases() {
		this.ownerRepository.deleteAll();
		this.addressRepository.deleteAll();
	}
	
	/**
	 * Read and Write test for Owner Class.
	 */
	@Test
	public void testPersistAndLoadOwner() {
		String first_name = "Walker";
		String last_name = "White";
		
		String email = "walker.68.white@hotmail.com";
		String password = "12398945354";
		
		Owner owner = new Owner(first_name, last_name, email, password);
		this.ownerRepository.save(owner);
		
		assertTrue(this.ownerRepository.existsByPersonID(owner.getPersonID()));
	}

	@Test
	public void testPersistAndLoadOwnerAddress() {
		String streetName = "Huntington Street";
		String streetNum = "123";
		String city = "DrummondVille";
		String postalCode = "H3A1B9";
		String country = "Canada";
		boolean isLocal = true;

		Address address = new Address(streetName, streetNum, city, postalCode, country, isLocal);

		this.addressRepository.save(address);

		String first_name = "Walker";
		String last_name = "White";

		String email = "walker.68.white@hotmail.com";
		String password = "12398945354";

		Owner owner = new Owner(first_name, last_name, email, password, address);
		this.ownerRepository.save(owner);

		Owner retrievedOwner = this.ownerRepository.findOwnerByPersonID(owner.getPersonID());
		assertNotNull(retrievedOwner);

		Address retrievedAddress = retrievedOwner.getAddress();
		assertNotNull(retrievedAddress);
		assertEquals(retrievedAddress.getCountry(), address.getCountry());
		assertEquals(retrievedAddress.getCity(), address.getCity());
	}
}
