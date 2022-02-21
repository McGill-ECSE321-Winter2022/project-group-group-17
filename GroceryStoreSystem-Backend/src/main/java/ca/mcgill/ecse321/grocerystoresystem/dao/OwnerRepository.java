package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Integer>{

	Owner findOwnerByEmail (String email);
	
}
