package ca.mcgill.ecse321.grocerystoresystem.service;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import  ca.mcgill.ecse321.grocerystoresystem.dao.*; //imports all the classes
import  ca.mcgill.ecse321.grocerystoresystem.model.*;

@Service 
public class GroceryStoreSystemService {
	@Autowired 
	AddressRepository addressRepository;
	@Autowired 
	CalendarRepository calendarRepository;
	@Autowired 
	CustomerRepository customerRepository;
	@Autowired 
	DeliveryOrderRepository deliveryOrderRepository;
	@Autowired 
	EmployeeRepository employeeRepository;
	@Autowired 
	InStoreOrderRepository inStoreOrderRepository;
	@Autowired 
	ItemRepository itemRepository;
	@Autowired 
	ItemQuantityRepository itemQuantityRepository;
	@Autowired 
	OwnerRepository ownerRepository;
	@Autowired 
	PersonRepository personRepository;
	@Autowired 
	PickupOrderRepository pickupOrderRepository;
	@Autowired 
	ShiftRepository shiftRepository;
	@Autowired 
	SpecialDayRepository specialDayRepository;
	@Autowired 
	StoreHourRepository storeHourRepository;
	
	@Transactional
	public Address createAddress(int addressID,String streetName, String streetNum, String city, String postalCode, String country, boolean isLocal) {
		Address address = new Address();
		address.setAddressID(addressID);
		address.setCity(city);
		address.setCountry(country);
		address.setLocal(isLocal);
		address.setPostalCode(postalCode);
		address.setStreetName(streetName);
		address.setStreetNum(streetNum);
		addressRepository.save(address);
		return address;
	}	
	@Transactional
	public Address getAddress(int addressID) {
		Address address = addressRepository.findAddressByAddressID(addressID);
		return address;
	}
	@Transactional
	public List<Address> getAllAddresses(){
		return toList(addressRepository.findAll()); 
	}
	
}
