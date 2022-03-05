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
public class GroceryStoreSystemAddressService {
	@Autowired 
	AddressRepository addressRepository;
	/**
	 * @author Tinetendo Wamambo
	 * @param streetName
	 * @param streetNum
	 * @param city
	 * @param postalCode
	 * @param country
	 * @param isLocal
	 * @return address
	 * This method is used to create a new address in the system and add it to the existing address repository.
	 * It also check the validity of each parameter input and throws exceptions when the user violates certain rules. 
	 */
	@Transactional
	public Address createAddress(String streetName, String streetNum, String city, String postalCode, String country, boolean isLocal) {
		Address address = new Address();
		try {
			checkEmptyInput(streetName);
			checkEmptyInput(streetNum);
			checkEmptyInput(city);
			checkEmptyInput(postalCode);
			checkEmptyInput(country);
			checkEmptyInput(isLocal);
			
		}catch(IllegalArgumentException e){
			System.out.println(e);
		}
		
		try {
			checkAlpha(streetName);
			checkAlpha(city);
			checkAlpha(country);
			checkAlphaNum(streetNum);
			checkAlphaNum(postalCode);
			
			address.setCity(city);
			address.setCountry(country);
			address.setLocal(isLocal);
			address.setPostalCode(postalCode);
			address.setStreetName(streetName);
			address.setStreetNum(streetNum);
			}catch(IllegalArgumentException e) {
			System.out.println(e);
		}

		addressRepository.save(address);
		return address;
	}
	private void checkEmptyInput(Object input) {
		if(input == null) {
			throw new IllegalArgumentException("You must enter all the required information to proceed.");
		}
	}
	private void checkAlpha(String word) {
		int i = 0;
		while (i<word.length()) {
			if (Character.isAlphabetic(word.charAt(i))==false) {
				throw new IllegalArgumentException("Your input should not contain any special characters.");
			}
			i++;
		}
	}
	private void checkAlphaNum(String number) {
		int i = 0;
		while(i<number.length()) {
			if (Character.isLetterOrDigit(number.charAt(i))== false) {
				throw new IllegalArgumentException("Your input should only contain digits or numbers.");
			}
		}
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
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
