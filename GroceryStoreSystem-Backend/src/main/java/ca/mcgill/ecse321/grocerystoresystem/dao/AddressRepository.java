 package ca.mcgill.ecse321.grocerystoresystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Address;

 public interface AddressRepository extends CrudRepository<Address, Integer>{
	/**
	 * Find Address based on their ID
	 */
	Address findAddressByAddressID(Integer addressID);
	List <Address> findAddressByStreetName(String streetName); //Accommodate for multiple addresses
	List <Address> findAddressByStreetNum(String streetNum);
	List <Address> findAddressByCity(String city);
	List <Address> findAddressByCountry(String country);
	List <Address> findAddressByPostalCode(String postalCode);
	List <Address> findAddressByIsLocal(boolean isLocal);
	List <Address> findAddressByStreetNumAndStreetName(String streetNum, String streetName);
   
	/**
	 * Returns true if their is an address in the database with the passed ID as parameter
	 */
	boolean existsByAddressID(Integer addressID);
	boolean existsByStreetName(String streetName);
	boolean existsByStreetNum(String streetNum);
	boolean existsByCity(String city);
	boolean existsByCountry(String country);
	boolean existsByPostalCode(String postalCode);
	boolean existsByStreetNumAndStreetName(String streetNum, String streetName);
}
