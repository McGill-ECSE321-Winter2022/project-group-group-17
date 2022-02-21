package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer>{
	
	Address findAddressByAddressID(Integer addressID);
}
