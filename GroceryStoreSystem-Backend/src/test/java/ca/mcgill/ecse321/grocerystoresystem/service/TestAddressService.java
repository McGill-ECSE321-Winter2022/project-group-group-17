package ca.mcgill.ecse321.grocerystoresystem.service;

import ca.mcgill.ecse321.grocerystoresystem.dao.AddressRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
@ExtendWith(MockitoExtension.class)
public class TestAddressService {
	@Mock
	private AddressRepository addressRepository;
	
	@InjectMocks
	private AddressService addressService;
	
	private static final int NO_ADDRESS_ID = 0000;
	
	private static final int ADDRESS_ID = 1000;
	private static final String STREET_NAME = "Peel";
	private static final String STREET_NUM = "1234";
	private static final String CITY = "Montreal";
	private static final String COUNTRY = "Canada";
	private static final String POSTAL_CODE = "HHHHHH";
	private static final Boolean IS_LOCAL  = true;
	

	private static final int ADDRESS_ID2 = 1001;
	private static final String STREET_NAME2 = "Peel";
	private static final String STREET_NUM2 = "1235";
	private static final String CITY2 = "Montreal";
	private static final String COUNTRY2 = "Canada";
	private static final String POSTAL_CODE2 = "H2HH2H";
	private static final Boolean IS_LOCAL2  = true;
	

	private static final int ADDRESS_ID3 = 1003;
	private static final String STREET_NAME3 = "Monkland";
	private static final String STREET_NUM3 = "1236";
	private static final String CITY3 = "Quebec";
	private static final String COUNTRY3 = "Canada";
	private static final String POSTAL_CODE3 = "H3HH3H";
	private static final Boolean IS_LOCAL3  = false;

	private Address createAddress() {
		Address address = new Address();
		address.setAddressID(ADDRESS_ID);
		address.setCity(CITY);
		address.setCountry(COUNTRY);
		address.setLocal(IS_LOCAL);
		address.setPostalCode(POSTAL_CODE);
		address.setStreetName(STREET_NAME);
		address.setStreetNum(STREET_NAME);
		
		return address;
	}
	private List<Address> createAddresses() {
		ArrayList<Address> addresses = new ArrayList<>();
		
		Address address = new Address();
		address.setAddressID(ADDRESS_ID);
		address.setCity(CITY);
		address.setCountry(COUNTRY);
		address.setLocal(IS_LOCAL);
		address.setPostalCode(POSTAL_CODE);
		address.setStreetName(STREET_NAME);
		address.setStreetNum(STREET_NAME);
		
		Address address2 = new Address();
		address2.setAddressID(ADDRESS_ID2);
		address2.setCity(CITY2);
		address2.setCountry(COUNTRY2);
		address2.setLocal(IS_LOCAL2);
		address2.setPostalCode(POSTAL_CODE2);
		address2.setStreetName(STREET_NAME2);
		address2.setStreetNum(STREET_NAME2);
		
		addresses.add(address);
		addresses.add(address2);
		return addresses;
	}
	private List<Address> createAllAddresses() {
		ArrayList<Address> addresses = new ArrayList<>();
		Address address = new Address();
		address.setAddressID(ADDRESS_ID);
		address.setCity(CITY);
		address.setCountry(COUNTRY);
		address.setLocal(IS_LOCAL);
		address.setPostalCode(POSTAL_CODE);
		address.setStreetName(STREET_NAME);
		address.setStreetNum(STREET_NAME);
		
		Address address2 = new Address();
		address2.setAddressID(ADDRESS_ID2);
		address2.setCity(CITY2);
		address2.setCountry(COUNTRY2);
		address2.setLocal(IS_LOCAL2);
		address2.setPostalCode(POSTAL_CODE2);
		address2.setStreetName(STREET_NAME2);
		address2.setStreetNum(STREET_NAME2);
		
		Address address3 = new Address();
		address3.setAddressID(ADDRESS_ID3);
		address3.setCity(CITY3);
		address3.setCountry(COUNTRY3);
		address3.setLocal(IS_LOCAL3);
		address3.setPostalCode(POSTAL_CODE3);
		address3.setStreetName(STREET_NAME3);
		address3.setStreetNum(STREET_NAME3);
		
		addresses.add(address);
		addresses.add(address2);
		addresses.add(address3);
		return addresses;
	}
	@BeforeEach
	public void setMockOutput(){
		lenient().when(addressRepository.findAddressByAddressID(anyInt())).thenAnswer((InvocationOnMock invocation)->{
			if(invocation.getArgument(0).equals(ADDRESS_ID)) {
				return createAddress();
			}else{
				return null;
			}
		});
		lenient().when(addressRepository.findAddressByPostalCode(anyString())).thenAnswer((InvocationOnMock invocation)->{
			if(invocation.getArgument(0).equals(POSTAL_CODE)) {
				return createAddress();
			}else{
				return null;
			}
		});
		lenient().when(addressRepository.findAddressByStreetNumAndStreetName(anyString(),anyString())).thenAnswer((InvocationOnMock invocation)->{
			if(invocation.getArgument(0).equals(STREET_NUM)&&invocation.getArgument(1).equals(STREET_NAME)) {
				return createAddress();
			}else{
				return null;
			}
		});
	    lenient().when(addressRepository.findAddressByStreetName(anyString())).thenAnswer( (InvocationOnMock invocation)->{
	         if(invocation.getArgument(0).equals(STREET_NAME)) {
	             List<Address> addresses = createAddresses();
	            return addresses;
	        } else {
	            return new ArrayList<>();
	        }
	    });	
		lenient().when(addressRepository.findAddressByStreetNum(anyString())).thenAnswer((InvocationOnMock invocation)->{
			if(invocation.getArgument(0).equals(STREET_NUM)) {
				return createAddress();
			}else{
				return null;
			}
		});
	    lenient().when(addressRepository.findAddressByCity(anyString())).thenAnswer( (InvocationOnMock invocation)->{
	         if(invocation.getArgument(0).equals(CITY)) {
	             List<Address> addresses = createAddresses();
	            return addresses;
	        } else {
	            return new ArrayList<>();
	        }
	    });	
	    lenient().when(addressRepository.findAddressByCountry(anyString())).thenAnswer( (InvocationOnMock invocation)->{
	         if(invocation.getArgument(0).equals(COUNTRY)) {
	             List<Address> addresses = createAllAddresses();
	            return addresses;
	        } else {
	            return new ArrayList<>();
	        }
	    });	
	    lenient().when(addressRepository.findAddressByIsLocal(anyBoolean())).thenAnswer( (InvocationOnMock invocation)->{
	         if(invocation.getArgument(0).equals(IS_LOCAL)) {
	             List<Address> addresses = createAddresses();
	            return addresses;
	        } else {
	            return new ArrayList<>();
	        }
	    });	
	    lenient().when(addressRepository.findAddressByAddressID(anyInt())).thenAnswer(
	    		(InvocationOnMock invocation) -> invocation.getArgument(0).equals(ADDRESS_ID));
	    lenient().when(addressRepository.findAddressByStreetName(anyString())).thenAnswer(
	    		(InvocationOnMock invocation) -> invocation.getArgument(0).equals(STREET_NAME));
	    lenient().when(addressRepository.findAddressByStreetNum(anyString())).thenAnswer(
	    		(InvocationOnMock invocation) -> invocation.getArgument(0).equals(STREET_NUM));
	    lenient().when(addressRepository.findAddressByPostalCode(anyString())).thenAnswer(
	    		(InvocationOnMock invocation) -> invocation.getArgument(0).equals(POSTAL_CODE));
	    lenient().when(addressRepository.findAddressByCity(anyString())).thenAnswer(
	    		(InvocationOnMock invocation) -> invocation.getArgument(0).equals(CITY));
	    lenient().when(addressRepository.findAddressByCountry(anyString())).thenAnswer(
	    		(InvocationOnMock invocation) -> invocation.getArgument(0).equals(COUNTRY));
	    lenient().when(addressRepository.findAddressByIsLocal(anyBoolean())).thenAnswer(
	    		(InvocationOnMock invocation) -> invocation.getArgument(0).equals(IS_LOCAL));
	    lenient().when(addressRepository.findAddressByStreetNumAndStreetName(anyString(),anyString())).thenAnswer(
	    		(InvocationOnMock invocation) -> invocation.getArgument(0).equals(STREET_NUM)&& invocation.getArgument(1).equals(STREET_NAME));   
	    lenient().when(addressRepository.findAll()).thenAnswer(
	    		(InvocationOnMock invocation) -> createAllAddresses());
	    Answer<?> returnParamAsAnswer = (InvocationOnMock invocation) -> {return invocation.getArgument(0);};
	    lenient().when(addressRepository.save(any(Address.class))).thenAnswer(returnParamAsAnswer);
	}
	@Test
	public void testCreateAddress() {
		Address savedAddress = this.addressService.createAddress(ADDRESS_ID,STREET_NAME, STREET_NUM, CITY, POSTAL_CODE, COUNTRY, IS_LOCAL);
		
		assertNotNull(savedAddress);
		assertEquals(ADDRESS_ID,savedAddress.getAddressID());
		assertEquals(STREET_NAME, savedAddress.getStreetName());
		assertEquals(STREET_NUM, savedAddress.getStreetNum());
		assertEquals(CITY, savedAddress.getCity());
		assertEquals(POSTAL_CODE, savedAddress.getPostalCode());
		assertEquals(COUNTRY, savedAddress.getCountry());
		assertEquals(IS_LOCAL, savedAddress.isLocal());
		
	}
}
