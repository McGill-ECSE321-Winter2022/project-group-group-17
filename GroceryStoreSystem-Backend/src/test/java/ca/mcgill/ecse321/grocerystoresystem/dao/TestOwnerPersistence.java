package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.Owner;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestOwnerPersistence {
	
	@Autowired
	private OwnerRepository ownerRepository;

	/**
	 * Cleaning the database after the test
	 */
	@AfterEach
	public void clearDatabases() {
		this.ownerRepository.deleteAll();
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
	
	
}
