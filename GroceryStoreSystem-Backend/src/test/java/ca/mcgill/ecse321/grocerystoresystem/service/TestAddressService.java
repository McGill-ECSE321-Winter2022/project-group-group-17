package ca.mcgill.ecse321.grocerystoresystem.service;
import ca.mcgill.ecse321.grocerystoresystem.dao.*;
import ca.mcgill.ecse321.grocerystoresystem.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)

public class TestAddressService {
	@Mock 
	private AddressRepository addressRepository;
	
	@InjectMocks
	private AddressService addressService;
	
	private static final int NO_ADDRESS_KEY = 234;
	
	private static final int ADDRESS_KEY = 1001;
	private static final String STREET_NUM = "1239";
	private static final String STREET_NAME = "Peel";
	private static final String CITY = "Montreal";
	private static final String POSTAL_CODE = "HHH HHH";
	private static final String COUNTRY = "Canada";
	private static final boolean IS_LOCAL = true;
	
	private static final int ADDRESS_KEY_2 = 1002;
	private static final String STREET_NUM_2 = "1234";
	private static final String STREET_NAME_2 = "Peel";
	private static final String CITY_2 = "Montreal";
	private static final String POSTAL_CODE_2 = "HH4 3HH";
	private static final String COUNTRY_2 = "Canada";
	private static final boolean IS_LOCAL_2 = true;
	
	private static final int ADDRESS_KEY_3= 1003;
	private static final String STREET_NUM_3 = "1230";
	private static final String STREET_NAME_3 = "Ottawa";
	private static final String CITY_3 = "Quebec";
	private static final String POSTAL_CODE_3 = "H3H H4H";
	private static final String COUNTRY_3 = "Canada";
	private static final boolean IS_LOCAL_3 = false;
	
	private Address createAddress() {
		Address address = new Address();
	        address.setAddressID(ADDRESS_KEY);
	        address.setStreetName(STREET_NAME);
	        address.setStreetNum(STREET_NUM);
	        address.setCity(CITY);
	        address.setCountry(COUNTRY);
	        address.setPostalCode(POSTAL_CODE);
	        address.setLocal(IS_LOCAL);
	       
	        return address;
	}
	private List<Address> createAddresses(){
		ArrayList<Address> addresses = new ArrayList<>();
		
		Address address = new Address();
	    address.setAddressID(ADDRESS_KEY);
	    address.setStreetName(STREET_NAME);
	    address.setStreetNum(STREET_NUM);
	    address.setCity(CITY);
	    address.setCountry(COUNTRY);
	    address.setPostalCode(POSTAL_CODE);
	    address.setLocal(IS_LOCAL);
	      
	    Address address2 = new Address();
	    address2.setAddressID(ADDRESS_KEY_2);
	    address2.setStreetName(STREET_NAME_2);
	    address2.setStreetNum(STREET_NUM_2);
	    address2.setCity(CITY_2);
	    address2.setCountry(COUNTRY_2);
	    address2.setPostalCode(POSTAL_CODE_2);
	    address2.setLocal(IS_LOCAL_2); 
	    addresses.add(address2);
	    addresses.add(address);
	    return addresses;
	}
	private List<Address> createAllAddresses(){
		ArrayList<Address> addresses = new ArrayList<>();
		
		Address address = new Address();
	    address.setAddressID(ADDRESS_KEY);
	    address.setStreetName(STREET_NAME);
	    address.setStreetNum(STREET_NUM);
	    address.setCity(CITY);
	    address.setCountry(COUNTRY);
	    address.setPostalCode(POSTAL_CODE);
	    address.setLocal(IS_LOCAL);
	      
	    Address address2 = new Address();
	    address2.setAddressID(ADDRESS_KEY_2);
	    address2.setStreetName(STREET_NAME_2);
	    address2.setStreetNum(STREET_NUM_2);
	    address2.setCity(CITY_2);
	    address2.setCountry(COUNTRY_2);
	    address2.setPostalCode(POSTAL_CODE_2);
	    address2.setLocal(IS_LOCAL_2); 
	    
	    Address address3 = new Address();
	    address3.setAddressID(ADDRESS_KEY_3);
	    address3.setStreetName(STREET_NAME_3);
	    address3.setStreetNum(STREET_NUM_3);
	    address3.setCity(CITY_3);
	    address3.setCountry(COUNTRY_3);
	    address3.setPostalCode(POSTAL_CODE_3);
	    address3.setLocal(IS_LOCAL_3); 
	    
	    addresses.add(address3);
	    addresses.add(address2);
	    addresses.add(address);
	    return addresses;
	}
	@BeforeEach
	public void setMockOutPut() {
	     lenient().when(addressRepository.findAddressByAddressID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	            if(invocation.getArgument(0).equals(ADDRESS_KEY)) {
	                return createAddress();
	            } else {
	                return null;
	            }
	        });
	     lenient().when(addressRepository.findAddressByCity(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	            if(invocation.getArgument(0).equals(CITY)) {
	                 List<Address> addresses = new ArrayList<>();
	                 addresses.add(createAddress());

	                 return addresses;
	            } else {
	                return new ArrayList<>();
	            }
	        }); 
	     lenient().when(addressRepository.findAddressByCountry(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	            if(invocation.getArgument(0).equals(COUNTRY)) {
	                 return createAllAddresses();
	            } else {
	                return new ArrayList<>();
	            }
	        }); 
	     lenient().when(addressRepository.findAddressByStreetName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	            if(invocation.getArgument(0).equals(STREET_NAME)) {
	                 return createAddresses();
	            } else {
	                return new ArrayList<>();
	            }
	        }); 
	     lenient().when(addressRepository.findAddressByStreetNum(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	            if(invocation.getArgument(0).equals(STREET_NUM)) {
	                List<Address> addresses = new ArrayList<>();
	                addresses.add(createAddress());
	            	return addresses;
	            } else {
	                return new ArrayList<>();
	            }
	        }); 
	     lenient().when(addressRepository.findAddressByPostalCode(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	            if(invocation.getArgument(0).equals(POSTAL_CODE)) {
	            	   List<Address> addresses = new ArrayList<>();
		               addresses.add(createAddress());
		               return addresses;
	            } else {
	                return new ArrayList<>();
	            }
	        }); 
	     lenient().when(addressRepository.findAddressByIsLocal(anyBoolean())).thenAnswer( (InvocationOnMock invocation) -> {
	            if(invocation.getArgument(0).equals((IS_LOCAL))) {
	                 return createAddresses();
	            } else {
	                return new ArrayList<>();
	            }
	        }); 
	     lenient().when(addressRepository.findAddressByStreetNumAndStreetName(anyString(),anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	            if(invocation.getArgument(0).equals(STREET_NUM)&& invocation.getArgument(1).equals(STREET_NAME)) {
	                List<Address> addresses = new ArrayList<>();
	                addresses.add(createAddress());
	            	return addresses;
	            } else {
	                return new ArrayList<>();
	            }
	        }); 
	     lenient().when(addressRepository.findAddressByAddressID(anyInt())).thenAnswer(
	                (InvocationOnMock invocation) -> invocation.getArgument(0).equals(ADDRESS_KEY));
	     lenient().when(addressRepository.findAddressByCity(anyString())).thenAnswer(
	                (InvocationOnMock invocation) -> invocation.getArgument(0).equals(CITY));
	     lenient().when(addressRepository.findAddressByCountry(anyString())).thenAnswer(
	                (InvocationOnMock invocation) -> invocation.getArgument(0).equals(COUNTRY));
	     lenient().when(addressRepository.findAddressByIsLocal(anyBoolean())).thenAnswer(
	                (InvocationOnMock invocation) -> invocation.getArgument(0).equals(IS_LOCAL));
	     lenient().when(addressRepository.findAddressByPostalCode(anyString())).thenAnswer(
	                (InvocationOnMock invocation) -> invocation.getArgument(0).equals(POSTAL_CODE));
	     lenient().when(addressRepository.findAddressByStreetName(anyString())).thenAnswer(
	                (InvocationOnMock invocation) -> invocation.getArgument(0).equals(STREET_NAME));
	     lenient().when(addressRepository.findAddressByStreetNum(anyString())).thenAnswer(
	                (InvocationOnMock invocation) -> invocation.getArgument(0).equals(STREET_NUM));
	     lenient().when(addressRepository.findAddressByStreetNumAndStreetName(anyString(), anyString())).thenAnswer(
	                (InvocationOnMock invocation) -> invocation.getArgument(0).equals(STREET_NUM)&& invocation.getArgument(1).equals(STREET_NAME));
	     lenient().when(addressRepository.findAll()).thenAnswer(
	                (InvocationOnMock invocation) -> createAllAddresses());
	     
	     Answer<?> returnParamAsAnswer = (InvocationOnMock invocation) -> {return invocation.getArgument(0);};
	        lenient().when(addressRepository.save(any(Address.class))).thenAnswer(returnParamAsAnswer);
	    }
	
	@Test
	public void testCreateAddress() {
		Address savedAddress = this.addressService.createAddress(ADDRESS_KEY,STREET_NAME, STREET_NUM, CITY, POSTAL_CODE, COUNTRY, IS_LOCAL);
		assertNotNull(savedAddress);
		assertEquals(savedAddress.getStreetName(), STREET_NAME);
		assertEquals(savedAddress.getStreetNum(), STREET_NUM);
		assertEquals(savedAddress.getCity(), CITY);
		assertEquals(savedAddress.getPostalCode(),POSTAL_CODE);
		assertEquals(savedAddress.getCountry(), COUNTRY);
		assertEquals(savedAddress.isLocal(), IS_LOCAL);
		assertEquals(savedAddress.getAddressID(), ADDRESS_KEY);	
	}
	@Test 
	public void testCreateAddressStreetNameFail() {
		Address savedAddress = null;
		String error = null;
		try {
			savedAddress = this.addressService.createAddress("",STREET_NUM, CITY, POSTAL_CODE, COUNTRY, IS_LOCAL);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(savedAddress);
		assertEquals(error,"You must enter all the required information to proceed.");	
	}
	@Test 
	public void testCreateAddressStreetNumFail() {
		Address savedAddress = null;
		String error = null;
		try {
			savedAddress = this.addressService.createAddress(STREET_NAME,"", CITY, POSTAL_CODE, COUNTRY, IS_LOCAL);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(savedAddress);
		assertEquals(error,"You must enter all the required information to proceed.");	
	}
	@Test 
	public void testCreateAddressCityFail() {
		Address savedAddress = null;
		String error = null;
		try {
			savedAddress = this.addressService.createAddress(STREET_NAME,STREET_NUM, "", POSTAL_CODE, COUNTRY, IS_LOCAL);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(savedAddress);
		assertEquals(error,"You must enter all the required information to proceed.");	
	}
	@Test 
	public void testCreateAddressPostalCodeFail() {
		Address savedAddress = null;
		String error = null;
		try {
			savedAddress = this.addressService.createAddress(STREET_NAME,STREET_NUM, CITY, "", COUNTRY, IS_LOCAL);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(savedAddress);
		assertEquals(error,"You must enter all the required information to proceed.");	
	}
	@Test 
	public void testCreateAddressCountryFail() {
		Address savedAddress = null;
		String error = null;
		try {
			savedAddress = this.addressService.createAddress(ADDRESS_KEY,STREET_NAME,STREET_NUM, CITY, POSTAL_CODE, "", IS_LOCAL);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(savedAddress);
		assertEquals(error,"You must enter all the required information to proceed.");	
	}
	@Test 
	public void testGetAddressByIDSuccessful() {
		Address address = null;
		
		try {
			address = addressService.getAddressWithID(ADDRESS_KEY);
			
		}catch(NullPointerException e) {
			fail(e.getMessage());
		}
		assertNotNull(address);	
		assertEquals(address.getStreetName(), STREET_NAME);
		assertEquals(address.getStreetNum(), STREET_NUM);
		assertEquals(address.getCity(), CITY);
		assertEquals(address.getPostalCode(),POSTAL_CODE);
		assertEquals(address.getCountry(), COUNTRY);
		assertEquals(address.isLocal(), IS_LOCAL);
		assertEquals(address.getAddressID(), ADDRESS_KEY);	
	}
	@Test 
	public void testGetAddressByIDUnsuccessful() {
		Address address = null;
		String error = null;
	
		try {
			address = this.addressService.getAddressWithID(NO_ADDRESS_KEY);
			
		}catch(NullPointerException e) {
			error = e.getMessage();
		}
		assertNull(address);	
		assertEquals(error,"Address not found");
	}
	@Test 
	public void testGetAddressByStreetNameSuccessful() {
		 List<Address> addresses = new ArrayList<>();
	
		try {
			addresses = this.addressService.getAddressWithStreetName(STREET_NAME);
			
		}catch(NullPointerException e) {
			fail(e.getMessage());
		}
		assertEquals(addresses.size(),2);	
		Address address = addresses.get(0);
		Address address2 = addresses.get(1);
		
		assertNotNull(address);
		assertNotNull(address2);
		
		assertEquals(address.getStreetName(), STREET_NAME);
		assertEquals(address.getStreetNum(), STREET_NUM);
		assertEquals(address.getCity(), CITY);
		assertEquals(address.getPostalCode(),POSTAL_CODE);
		assertEquals(address.getCountry(), COUNTRY);
		assertEquals(address.isLocal(), IS_LOCAL);
		assertEquals(address.getAddressID(), ADDRESS_KEY);	
		
		assertEquals(address2.getStreetName(), STREET_NAME_2);
		assertEquals(address2.getStreetNum(), STREET_NUM_2);
		assertEquals(address2.getCity(), CITY_2);
		assertEquals(address2.getPostalCode(),POSTAL_CODE_2);
		assertEquals(address2.getCountry(), COUNTRY_2);
		assertEquals(address2.isLocal(), IS_LOCAL_2);
		assertEquals(address2.getAddressID(), ADDRESS_KEY_2);		
	}
	@Test 
	public void testGetAddressByStreetNameUnsuccessful() {
		 List<Address> addresses = null;
		 String error = null;
		 
		 try {
			 addresses = this.addressService.getAddressWithStreetName("Monkland");
		 } catch(NullPointerException e){
			 error = e.getMessage();
		 }
		 assertNull(addresses);
		 assertEquals("There are no addresses with that street name", error);
	}
	@Test 
	public void testGetAddressByStreetNameUnsuccessful2() {
		 List<Address> addresses = null;
		 String error = null;
		 
		 try {
			 addresses = this.addressService.getAddressWithStreetName("");
		 } catch(IllegalArgumentException e){
			 error = e.getMessage();
		 }
		 assertNull(addresses);
		 assertEquals("Please provide a valid street name", error);
	}
	@Test
	public void testGetAddressByStreetNumSuccessful() {
		 List<Address> addresses = new ArrayList<>();
		 try {
			 addresses = this.addressService.getAddressWithStreetNum(STREET_NUM); 
		 } catch(NullPointerException e){
			 fail(e.getMessage());
		 }
		 assertEquals(addresses.size(),1);	
		 Address address = addresses.get(0);
		 
		 	assertNotNull(address);	
			assertEquals(address.getStreetName(), STREET_NAME);
			assertEquals(address.getStreetNum(), STREET_NUM);
			assertEquals(address.getCity(), CITY);
			assertEquals(address.getPostalCode(),POSTAL_CODE);
			assertEquals(address.getCountry(), COUNTRY);
			assertEquals(address.isLocal(), IS_LOCAL);
			assertEquals(address.getAddressID(), ADDRESS_KEY);	
	}
	@Test 
	public void testGetAddressByStreetNumberUnsuccessful() {
		 List<Address> addresses = null;
		 String error = null;
		 
		 try {
			 addresses = this.addressService.getAddressWithStreetNum("0000");
		 } catch(NullPointerException e){
			 error = e.getMessage();
		 }
		 assertNull(addresses);
		 assertEquals("There are no addresses with that street number", error);
	}
	@Test 
	public void testGetAddressByStreetNumberUnsuccessful2() {
		 List<Address> addresses = null;
		 String error = null;
		 
		 try {
			 addresses = this.addressService.getAddressWithStreetNum("");
		 } catch(IllegalArgumentException  e){
			 error = e.getMessage();
		 }
		 assertNull(addresses);
		 assertEquals("Please provide a valid street number", error);
	}
	@Test
	public void testGetAddressByStreetNumAndNameSuccessful() {
		 List<Address> addresses = new ArrayList<>();
		 try {
			 addresses = this.addressService.getAddressWithStreetNumAndName(STREET_NAME, STREET_NUM); 
		 } catch(NullPointerException e){
			 fail(e.getMessage());
		 }
		 assertEquals(addresses.size(),1);	
		 Address address = addresses.get(0);
		 
		 	assertNotNull(address);	
			assertEquals(address.getStreetName(), STREET_NAME);
			assertEquals(address.getStreetNum(), STREET_NUM);
			assertEquals(address.getCity(), CITY);
			assertEquals(address.getPostalCode(),POSTAL_CODE);
			assertEquals(address.getCountry(), COUNTRY);
			assertEquals(address.isLocal(), IS_LOCAL);
			assertEquals(address.getAddressID(), ADDRESS_KEY);	
	}
	@Test
	public void testGetAddressByStreetNumAndNameUnsuccessful() {
		 List<Address> addresses = null;
		 String error = null;
		 try {
			 addresses = this.addressService.getAddressWithStreetNumAndName("Monkland", "0000"); 
		 } catch(NullPointerException e){
			 error = (e.getMessage());
		 }
		 assertNull(addresses);
		 assertEquals("There are no addresses with that street name and number", error);
	}
	@Test
	public void testGetAddressByStreetNumAndNameUnsuccessful2() {
		 List<Address> addresses = null;
		 String error = null;
		 try {
			 addresses = this.addressService.getAddressWithStreetNumAndName("", "0000"); 
		 } catch(IllegalArgumentException  e){
			 error = (e.getMessage());
		 }
		 assertNull(addresses);
		 assertEquals("Please provide a valid street name", error);
	}
	@Test
	public void testGetAddressByStreetNumAndNameUnsuccessful3() {
		 List<Address> addresses = null;
		 String error = null;
		 try {
			 addresses = this.addressService.getAddressWithStreetNumAndName("Monkland", ""); 
		 } catch(IllegalArgumentException e){
			 error = (e.getMessage());
		 }
		 assertNull(addresses);
		 assertEquals("Please provide a valid street  number", error);
	}
	@Test 
	public void testGetAddressByCitySuccessful() {
		 List<Address> addresses = new ArrayList<>();
	
		try {
			addresses = this.addressService.getAddressWithCity(CITY);
			
		}catch(NullPointerException e) {
			fail(e.getMessage());
		}
		assertEquals(addresses.size(),2);	
		Address address = addresses.get(0);
		Address address2 = addresses.get(1);
		
		assertNotNull(address);
		assertNotNull(address2);
		
		assertEquals(address.getStreetName(), STREET_NAME);
		assertEquals(address.getStreetNum(), STREET_NUM);
		assertEquals(address.getCity(), CITY);
		assertEquals(address.getPostalCode(),POSTAL_CODE);
		assertEquals(address.getCountry(), COUNTRY);
		assertEquals(address.isLocal(), IS_LOCAL);
		assertEquals(address.getAddressID(), ADDRESS_KEY);	
		
		assertEquals(address2.getStreetName(), STREET_NAME_2);
		assertEquals(address2.getStreetNum(), STREET_NUM_2);
		assertEquals(address2.getCity(), CITY_2);
		assertEquals(address2.getPostalCode(),POSTAL_CODE_2);
		assertEquals(address2.getCountry(), COUNTRY_2);
		assertEquals(address2.isLocal(), IS_LOCAL_2);
		assertEquals(address2.getAddressID(), ADDRESS_KEY_2);		
	}
	@Test
	public void testGetAddressByCityUnsuccessful() {
		 List<Address> addresses = null;
		 String error = null;
		 try {
			 addresses = this.addressService.getAddressWithCity("NewYork"); 
		 } catch(NullPointerException e){
			 error = (e.getMessage());
		 }
		 assertNull(addresses);
		 assertEquals("There are no addresses with that city name.", error);
	}
	@Test
	public void testGetAddressByCityUnsuccessful2() {
		 List<Address> addresses = null;
		 String error = null;
		 try {
			 addresses = this.addressService.getAddressWithCity(""); 
		 } catch(IllegalArgumentException e){
			 error = (e.getMessage());
		 }
		 assertNull(addresses);
		 assertEquals("Please provide a valid city name.", error);
	}
	@Test 
	public void testGetAddressByIsLocalSuccessful() {
		 List<Address> addresses = new ArrayList<>();
	
		try {
			addresses = this.addressService.getAddressWithIsLocal(IS_LOCAL);
			
		}catch(NullPointerException e) {
			fail(e.getMessage());
		}
		assertEquals(addresses.size(),2);	
		Address address = addresses.get(0);
		Address address2 = addresses.get(1);
		
		assertNotNull(address);
		assertNotNull(address2);

		assertEquals(address.getStreetName(), STREET_NAME);
		assertEquals(address.getStreetNum(), STREET_NUM);
		assertEquals(address.getCity(), CITY);
		assertEquals(address.getPostalCode(),POSTAL_CODE);
		assertEquals(address.getCountry(), COUNTRY);
		assertEquals(address.isLocal(), IS_LOCAL);
		assertEquals(address.getAddressID(), ADDRESS_KEY);	
		
		assertEquals(address2.getStreetName(), STREET_NAME_2);
		assertEquals(address2.getStreetNum(), STREET_NUM_2);
		assertEquals(address2.getCity(), CITY_2);
		assertEquals(address2.getPostalCode(),POSTAL_CODE_2);
		assertEquals(address2.getCountry(), COUNTRY_2);
		assertEquals(address2.isLocal(), IS_LOCAL_2);
		assertEquals(address2.getAddressID(), ADDRESS_KEY_2);	
		}
	}
