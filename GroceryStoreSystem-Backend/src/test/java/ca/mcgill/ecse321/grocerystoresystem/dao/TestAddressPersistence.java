package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.Address;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestAddressPersistence {

	@Autowired
	private AddressRepository addressRepository;
	
	@AfterEach
	public void clearDatabases() {
		this.addressRepository.deleteAll();
	}
	
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
	}
}
