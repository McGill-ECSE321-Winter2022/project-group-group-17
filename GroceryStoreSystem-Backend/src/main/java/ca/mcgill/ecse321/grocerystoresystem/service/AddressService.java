package ca.mcgill.ecse321.grocerystoresystem.service;

import ca.mcgill.ecse321.grocerystoresystem.dao.AddressRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class AddressService {
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
	public Address createAddress(Integer addressID,String streetName, String streetNum, String city, String postalCode, String country, boolean isLocal) {

		try {

			checkEmptyInput(streetName);
			checkEmptyInput(streetNum);
			checkEmptyInput(city);
			checkEmptyInput(postalCode);
			checkEmptyInput(country);


		}catch(IllegalArgumentException e){

			return null;

		}

		try {
			checkAlpha(streetName);
			checkAlpha(city);
			checkAlpha(country);
			checkAlphaNum(streetNum);
			checkAlphaNum(postalCode);

		}catch(IllegalArgumentException e) {
			return null;

		}
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
	public Address createAddress(String streetName, String streetNum, String city, String postalCode, String country, boolean isLocal) {

		try {
			checkEmptyInput(streetName);
			checkEmptyInput(streetNum);
			checkEmptyInput(city);
			checkEmptyInput(postalCode);
			checkEmptyInput(country);


		}catch(IllegalArgumentException e){
			return null;

		}

		try {
			checkAlpha(streetName);
			checkAlpha(city);
			checkAlpha(country);
			checkAlphaNum(streetNum);
			checkAlphaNum(postalCode);


		}catch(IllegalArgumentException e) {
			return null;


		}
		Address address = new Address();

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
	public boolean deleteAddress(int addressID) {
		Address address = addressRepository.findAddressByAddressID(addressID);
		if(address == null) throw new NullPointerException("Address not found");
		addressRepository.delete(address);

		return true;
	}
	@Transactional
	public Address getAddressWithID(int addressID) {
		Address address = addressRepository.findAddressByAddressID(addressID);
		if(address == null) throw new NullPointerException("Address not found");
		return address;
	}
	@Transactional
	public List<Address> getAddressWithStreetName(String streetName) {
		try {
			checkAlpha(streetName);
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		if(streetName == null || streetName.length() == 0) throw new IllegalArgumentException("Please provide a valid street name");
		List<Address> addresses = this.addressRepository.findAddressByStreetName(streetName);

		if(addresses.size() ==0) throw new NullPointerException("There are no addresses with that street name");
		return addresses;
	}
	@Transactional
	public List<Address> getAddressWithStreetNum(String streetNum) {
		try {
			checkAlphaNum(streetNum);
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		if(streetNum == null || streetNum.length() == 0)throw new IllegalArgumentException("Please provide a valid street number.");
		List<Address> addresses = this.addressRepository.findAddressByStreetNum(streetNum);
		if (addresses.size()==0) throw new NullPointerException("There are no addresses with that street number");

		return addresses;
	}
	@Transactional
	public List<Address> getAddressWithStreetNumAndName(String streetNum, String streetName) {
		try {
			checkAlpha(streetName);
			checkAlphaNum(streetNum);
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		if(streetNum == null || streetNum.length() == 0)throw new IllegalArgumentException("Please provide a valid street number");
		if(streetName == null || streetName.length() == 0) throw new IllegalArgumentException("Please provide a valid street name");

		List<Address> addresses = this.addressRepository.findAddressByStreetNumAndStreetName(streetNum, streetName);
		if (addresses.size()==0) throw new NullPointerException("There are no addresses with that street name and number");
		return addresses;
	}
	@Transactional
	public List<Address> getAddressWithPostalCode(String postalCode) {
		try {
			checkAlphaNum(postalCode);
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		if(postalCode == null || postalCode.length() == 0)throw new IllegalArgumentException("Please provide a valid postal code.");
		List<Address> addresses = this.addressRepository.findAddressByPostalCode(postalCode);
		if (addresses.size()==0) throw new NullPointerException("There are no addresses with that postal code.");

		return addresses;
	}
	@Transactional
	public List<Address> getAddressWithCity(String city) {
		try {
			checkAlpha(city);
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		if(city == null || city.length() == 0)throw new IllegalArgumentException("Please provide a valid city name.");
		List<Address> addresses = this.addressRepository.findAddressByCity(city);
		if (addresses.size()==0) throw new NullPointerException("There are no addresses with that city name.");

		return addresses;
	}
	@Transactional
	public List<Address> getAddressWithIsLocal(boolean isLocal) {
		return toList (addressRepository.findAddressByIsLocal(isLocal));
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
	private void checkEmptyInput(String input) {
		if(input == null || input.equals("")) {
			throw new IllegalArgumentException("You must enter all the required information to proceed");
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
			i++;
		}
	}

}