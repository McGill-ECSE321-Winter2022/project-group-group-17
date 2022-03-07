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
	
	
	
}
