package ca.mcgill.ecse321.grocerystoresystem.service;

import ca.mcgill.ecse321.grocerystoresystem.dao.AddressRepository;
import ca.mcgill.ecse321.grocerystoresystem.dao.CustomerRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.Customer;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestCustomerService {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private static final int CUSTOMER_KEY = 1001;
    private static final int NO_CUSTOMER_KEY = 234;

    private static final String FIRST_NAME = "Yash";
    private static final String LAST_NAME = "Khapre";
    private static final String EMAIL = "yash.khapre@gmail.com";
    private static final String PASSWORD = "ya$hpas$word!";

    private static final int CUSTOMER_KEY_2 = 1002;
    private static final String FIRST_NAME2 = "Cristiano";
    private static final String LAST_NAME2 = "Ronaldo";
    private static final String EMAIL2 = "Cr7@gmail.com";
    private static final String PASSWORD2 = "siuuu1234";

    private static final int CUSTOMER_KEY_3 = 1003;
    private static final String FIRST_NAME3 = "Chelsea";
    private static final String LAST_NAME3 = "FootballClub";
    private static final String EMAIL3 = "chelseafc@gmail.com";
    private static final String PASSWORD3 = "w3Ar3Brokepl$help";
    
    private static final int CUSTOMER_KEY_4 = 1004;
    private static final String FIRST_NAME4 = "Y";
    private static final String LAST_NAME4 = "Khapre";
    private static final String EMAIL4 = "ykcfc09@gmail.com";
    private static final String PASSWORD4 = "randompassword";
    
    private static final int CUSTOMER_KEY_5 = 1005;
    private static final String FIRST_NAME5 = "Cristiano";
    private static final String LAST_NAME5 = "Ronaldo";
    private static final String EMAIL5 = "Lefauxcr7@gmail.com";
    private static final String PASSWORD5 = "123458998";
    
    private static final int CUSTOMER_KEY_6 = 1006;
    private static final String FIRST_NAME6 = "Random";
    private static final String LAST_NAME6 = "Person";
    private static final String EMAIL6 = "random@gmail.com";
    private static final String PASSWORD6 = "12345786";
    
    private static final int CUSTOMER_KEY_7 = 1007;
    private static final String FIRST_NAME7 = "Yash";
    private static final String LAST_NAME7 = "Khapre";
    private static final String EMAIL7 = "randomemail@email.com";
    private static final String PASSWORD7 = "999999999";

    private static final int ADDRESS_KEY = 1001;
    private static final String STREET_NUM = "401";
    private static final String STREET_NAME = "Sherbrooke";
    private static final String CITY = "Montreal";
    private static final String POSTAL_CODE = "H2X 2G1";
    private static final String COUNTRY = "Canada";
    private static final boolean IS_LOCAL = true;


    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setPersonID(CUSTOMER_KEY);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        customer.setEmail(EMAIL);
        customer.setPassword(PASSWORD);
        return customer;
    }

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

    private List<Customer> createCustomersWithSameLastName() {
        ArrayList<Customer> customers = new ArrayList<>();

        Customer customer= new Customer();
        customer.setPersonID(CUSTOMER_KEY);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        customer.setEmail(EMAIL);
        customer.setPassword(PASSWORD);

        Customer customer2 = new Customer();
        customer2.setPersonID(CUSTOMER_KEY_4);
        customer2.setFirstName(FIRST_NAME4);
        customer2.setLastName(LAST_NAME4);
        customer2.setEmail(EMAIL4);
        customer2.setPassword(PASSWORD4);

        customers.add(customer);
        customers.add(customer2);

        return customers;
    }

    private List<Customer> createCustomers2() {
        ArrayList<Customer> customers = new ArrayList<>();

        Customer customer3 = new Customer();
        customer3.setPersonID(CUSTOMER_KEY_3);
        customer3.setFirstName(FIRST_NAME3);
        customer3.setLastName(LAST_NAME3);
        customer3.setEmail(EMAIL3);
        customer3.setPassword(PASSWORD3);

        customers.add(customer3);

        return customers;
    }
    
    private List<Customer> createCustomersWithSameFullName() {
      ArrayList<Customer> customers = new ArrayList<>();

      Customer customer = new Customer();
      customer.setPersonID(CUSTOMER_KEY_2);
      customer.setFirstName(FIRST_NAME2);
      customer.setLastName(LAST_NAME2);
      customer.setEmail(EMAIL2);
      customer.setPassword(PASSWORD2);

      Customer customer2 = new Customer();
      customer2.setPersonID(CUSTOMER_KEY_5);
      customer2.setFirstName(FIRST_NAME5);
      customer2.setLastName(LAST_NAME5);
      customer2.setEmail(EMAIL5);
      customer2.setPassword(PASSWORD5);
      
      customers.add(customer);
      customers.add(customer2);
     
      return customers;
  }
    
    private Customer createCustomerWithAddress() {
      Customer savedCustomer = createCustomer();
      Address a = createAddress();
      savedCustomer.setAddress(a);
      return savedCustomer;
    }

    @BeforeEach
    public void setMockOutput() {
        lenient().when(customerRepository.findCustomerByPersonID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CUSTOMER_KEY)) {
                return createCustomer();
            } else {
                return null;
            }
        });
        
        lenient().when(customerRepository.findCustomersByFirstName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(FIRST_NAME)) {
              List<Customer> customers = new ArrayList<>();
              customers.add(createCustomer());
                return customers;
            } else {
                return new ArrayList<>();
            }
        });

        lenient().when(customerRepository.findCustomersByLastName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(LAST_NAME)) {
                return createCustomersWithSameLastName();

            } else {
                return new ArrayList<>();
            }
        });

        lenient().when(customerRepository.findCustomerByEmail(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(EMAIL)) {
                return createCustomer();
            } else {
                return null;
            }
        });

        lenient().when(customerRepository.findCustomersByFirstNameAndLastName(anyString(), anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(FIRST_NAME2) && invocation.getArgument(1).equals(LAST_NAME2)) {
                return createCustomersWithSameFullName();
            } else {
                return new ArrayList<>();
            }
        });

        lenient().when(addressRepository.findAddressByAddressID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ADDRESS_KEY)) {
                return createAddress();
            }
            else {
                return null;
            }
        });
        

        lenient().when(customerRepository.existsByPersonID(anyInt())).thenAnswer(
                (InvocationOnMock invocation) -> invocation.getArgument(0).equals(CUSTOMER_KEY));
        lenient().when(customerRepository.existsByFirstName(anyString())).thenAnswer(
                (InvocationOnMock invocation) -> invocation.getArgument(0).equals(FIRST_NAME));
        lenient().when(customerRepository.existsByLastName(anyString())).thenAnswer(
                (InvocationOnMock invocation) -> invocation.getArgument(0).equals(LAST_NAME));
        lenient().when(customerRepository.existsByEmail(anyString())).thenAnswer(
                (InvocationOnMock invocation) -> invocation.getArgument(0).equals(EMAIL));
        lenient().when(customerRepository.existsByFirstNameAndLastName(anyString(), anyString())).thenAnswer( (InvocationOnMock invocation)
                -> (invocation.getArgument(0).equals(FIRST_NAME) && invocation.getArgument(1).equals(LAST_NAME)));

        lenient().when(customerRepository.findAll()).thenAnswer( (InvocationOnMock invocation) -> createCustomers2());


        Answer<?> returnParamAsAnswer = (InvocationOnMock invocation) -> {return invocation.getArgument(0);};
        lenient().when(customerRepository.save(any(Customer.class))).thenAnswer(returnParamAsAnswer);
    }

    @Test
    public void testCreateCustomer() {
        Customer savedCustomer = null;

        try {
            savedCustomer = this.customerService.createCustomer(CUSTOMER_KEY_6, FIRST_NAME6, LAST_NAME6, EMAIL6, PASSWORD6, CITY, COUNTRY, POSTAL_CODE, 
                STREET_NAME, STREET_NUM, IS_LOCAL);
        }
        catch(IllegalArgumentException exp) {
            fail(exp.getMessage());
        }
        assertNotNull(savedCustomer);
        assertEquals(savedCustomer.getFirstName(), FIRST_NAME6);
        assertEquals(savedCustomer.getLastName(), LAST_NAME6);
        assertEquals(savedCustomer.getEmail(), EMAIL6);
        assertEquals(savedCustomer.getPassword(), PASSWORD6);
        assertEquals(savedCustomer.getAddress().getCity(), CITY);
        assertEquals(savedCustomer.getAddress().getCountry(), COUNTRY);
        assertEquals(savedCustomer.getAddress().getPostalCode(), POSTAL_CODE);
        assertEquals(savedCustomer.getAddress().getStreetName(), STREET_NAME);
        assertEquals(savedCustomer.getAddress().getStreetNum(), STREET_NUM);
    }

    @Test
    public void testCreateCustomer2() {
        Customer savedCustomer = null;
        try {
            savedCustomer = this.customerService.createCustomer(CUSTOMER_KEY_7, FIRST_NAME7, LAST_NAME7, EMAIL7, PASSWORD7, createAddress());
        }
        catch(IllegalArgumentException exp) {
            fail(exp.getMessage());
        }
        assertNotNull(savedCustomer);
        assertEquals(savedCustomer.getFirstName(), FIRST_NAME7);
        assertEquals(savedCustomer.getLastName(), LAST_NAME7);
        assertEquals(savedCustomer.getEmail(), EMAIL7);
        assertEquals(savedCustomer.getPassword(), PASSWORD7);

        assertNotNull(savedCustomer.getAddress());
        assertEquals(savedCustomer.getAddress().getStreetName(), STREET_NAME);
        assertEquals(savedCustomer.getAddress().getStreetNum(), STREET_NUM);
        assertEquals(savedCustomer.getAddress().getCity(), CITY);
        assertEquals(savedCustomer.getAddress().getCountry(), COUNTRY);
        assertEquals(savedCustomer.getAddress().getPostalCode(), POSTAL_CODE);
        assertEquals(savedCustomer.getAddress().isLocal(), IS_LOCAL);
        assertEquals(savedCustomer.getAddress().getAddressID(), ADDRESS_KEY);
    }

    @Test
    public void testCreateCustomerFail1() {
        Customer savedCustomer = null;
        String error = null;
        try {
            savedCustomer = this.customerService.createCustomer(CUSTOMER_KEY, null, LAST_NAME, EMAIL, PASSWORD);
        }
        catch(IllegalArgumentException exp) {
            error = exp.getMessage();
        }
        assertNull(savedCustomer);
        assertEquals(error, "Please provide valid First Name");
    }

    @Test
    public void testCreateCustomerFail2() {
        Customer savedCustomer= null;
        String error = null;

        try {
            savedCustomer = this.customerService.createCustomer(CUSTOMER_KEY, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, null);
        }
        catch(IllegalArgumentException exp) {
            error = exp.getMessage();
        }
        assertNull(savedCustomer);
        assertEquals(error, "Please provide valid Address");
    }

    @Test
    public void testGetCustomerByIDSuccessful() {
        Customer customer = null;
        try {
            customer = customerService.getCustomer(CUSTOMER_KEY);
        }
        catch(IllegalArgumentException exp) {
            fail(exp.getMessage());
        }
        assertNotNull(customer);
        assertEquals(CUSTOMER_KEY, customer.getPersonID());
        assertEquals(FIRST_NAME, customer.getFirstName());
        assertEquals(LAST_NAME, customer.getLastName());
        assertEquals(EMAIL, customer.getEmail());
        assertEquals(PASSWORD, customer.getPassword());
    }

    @Test
    public void testGetCustomerByIDUnsuccessful() {
        Customer customer = null;
        String error = null;
        try {
            customer = customerService.getCustomer(NO_CUSTOMER_KEY);
        }
        catch(IllegalArgumentException exp) {
            error = exp.getMessage();
        }
        assertNull(customer);
        assertEquals("Cannot find customer with specified ID", error);
    }

    @Test
    public void testGetCustomerByFirstNameSuccessful() {
        List<Customer> customers = null;
        try {
            customers = this.customerService.getCustomerByFirstName("Yash");
        }
        catch(IllegalArgumentException exp) {
            fail(exp.getMessage());
        }

        assertEquals(customers.size(), 1);
        Customer customer = customers.get(0);

        assertNotNull(customer);
        assertEquals(CUSTOMER_KEY, customer.getPersonID());
        assertEquals(FIRST_NAME, customer.getFirstName());
        assertEquals(LAST_NAME, customer.getLastName());
        assertEquals(EMAIL, customer.getEmail());
        assertEquals(PASSWORD, customer.getPassword());
    }

    @Test
    public void testGetCustomerByFirstNameUnsuccessful() {
        List<Customer> customers = null;
        String error = null;
        try {
            customers = this.customerService.getCustomerByFirstName("Yashi");
        }
        catch(IllegalArgumentException exp) {
            error = exp.getMessage();
        }

        assertNull(customers);
        assertEquals("Cannot find customers with specified first name", error);
    }

    @Test
    public void testGetCustomerByLastNameSuccessful() {
        List<Customer> customers = null;
        try {
            customers = this.customerService.getCustomerByLastName(LAST_NAME);
        }
        catch(IllegalArgumentException exp) {
            fail(exp.getMessage());
        }

        assertEquals(customers.size(), 2);
        assertNotNull(customers);
    }

    @Test
    public void testGetCustomerByLastNameUnsuccessful() {
        List<Customer> customers = null;
        String error = null;
        try {
            customers = this.customerService.getCustomerByLastName("manchesterutd");
        }
        catch(IllegalArgumentException exp) {
            error = exp.getMessage();
        }

        assertNull(customers);
        assertEquals("Cannot find customers with specified last name", error);
    }

    @Test
    public void testGetCustomerByEmailSuccessful() {
        Customer customer = null;
        try {
            customer  = this.customerService.getCustomerByEmail(EMAIL);
        }
        catch(IllegalArgumentException exp) {
            fail(exp.getMessage());
        }

        assertNotNull(customer);
        assertEquals(CUSTOMER_KEY, customer.getPersonID());
        assertEquals(FIRST_NAME, customer.getFirstName());
        assertEquals(LAST_NAME, customer.getLastName());
        assertEquals(EMAIL, customer.getEmail());
        assertEquals(PASSWORD, customer.getPassword());
    }

    @Test
    public void testGetCustomerByFullNameSuccessful() {
        List<Customer> customers = null;
        try {
            customers = this.customerService.getCustomerByFullName(FIRST_NAME2, LAST_NAME2);
        }
        catch(IllegalArgumentException exp) {
            fail(exp.getMessage());
        }

        assertEquals(2, customers.size());
        Customer customer = customers.get(0);
        Customer customer2 = customers.get(1);

        assertNotNull(customer);
        assertEquals(CUSTOMER_KEY_2, customer.getPersonID());
        assertEquals(FIRST_NAME2, customer.getFirstName());
        assertEquals(LAST_NAME2, customer.getLastName());
        assertEquals(EMAIL2, customer.getEmail());
        assertEquals(PASSWORD2, customer.getPassword());
        
        assertNotNull(customer2);
        assertEquals(CUSTOMER_KEY_5, customer2.getPersonID());
        assertEquals(FIRST_NAME5, customer2.getFirstName());
        assertEquals(LAST_NAME5, customer2.getLastName());
        assertEquals(EMAIL5, customer2.getEmail());
        assertEquals(PASSWORD5, customer2.getPassword());  
    }

    @Test
    public void testGetCustomerByEmailUnsuccessful() {
        Customer customer = null;
        String error = null;
        try {
            customer = this.customerService.getCustomerByEmail("email");
        }
        catch(IllegalArgumentException exp) {
            error = exp.getMessage();
        }

        assertNull(customer);
        assertEquals("Please enter a valid email", error);
    }
    
    @Test
    public void testGetCustomerByEmailUnsuccessful2() {
        Customer customer = null;
        String error = null;
        try {
            customer = this.customerService.getCustomerByEmail("email@email.com");
        }
        catch(IllegalArgumentException exp) {
            error = exp.getMessage();
        }

        assertNull(customer);
        assertEquals("Cannot find customer with specified email", error);
    }

    @Test
    public void testGetCustomerByFullNameUnsuccessful() {
        List<Customer> customers;
        String error = "";
        try {
            customers = this.customerService.getCustomerByFullName("yo","hello");
        }
        catch(IllegalArgumentException exp) {
            error = exp.getMessage();
        }
        assertEquals("Cannot find customers with specified full name", error);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = this.customerService.getAllCustomers();
        Customer customer = null;

        assertEquals(customers.size(), 1);
        customer = customers.get(0);

        assertEquals(customer.getPersonID(), CUSTOMER_KEY_3);
        assertEquals(customer.getFirstName(), FIRST_NAME3);
        assertEquals(customer.getLastName(), LAST_NAME3);
        assertEquals(customer.getEmail(), EMAIL3);
        assertEquals(customer.getPassword(), PASSWORD3);
    }

    @Test
    public void testIsCustomerByID() {
        boolean result = this.customerService.isCustomerByID(CUSTOMER_KEY);
        assertTrue(result);
    }

    @Test
    public void testIsCustomerByFirstName() {
        boolean result = this.customerService.isCustomerByFirstName(FIRST_NAME);
        assertTrue(result);
    }

    @Test
    public void testIsCustomerByLastName() {
        boolean result = this.customerService.isCustomerByLastName(LAST_NAME);
        assertTrue(result);
    }

    @Test
    public void testIsCustomerByEmail() {
        boolean result = this.customerService.isCustomerByEmail(EMAIL);
        assertTrue(result);
    }

    @Test
    public void testIsCustomerByFirstAndLastName() {
        boolean result = this.customerService.isCustomerByFirstAndLastName(FIRST_NAME, LAST_NAME);
        assertTrue(result);
    }

    @Test
    public void testIsCustomerByIDFail() {
        boolean result = this.customerService.isCustomerByID(9787);
        assertFalse(result);
    }

    @Test
    public void testIsCustomerByFirstNameFail() {
        boolean result = this.customerService.isCustomerByFirstName("YK");
        assertFalse(result);
    }

    @Test
    public void testIsCustomerByLastNameFail() {
        boolean result = this.customerService.isCustomerByLastName("YK");
        assertFalse(result);
    }

    @Test
    public void testIsCustomerByEmailFail() {
        boolean result = this.customerService.isCustomerByEmail("YK@gmail.com");
        assertFalse(result);
    }

    @Test
    public void testIsCustomerByFirstNameAndLastNameFail() {
        boolean result = this.customerService.isCustomerByFirstAndLastName("Y", "K");
        assertFalse(result);
    }

    @Test
    public void testUpdateCustomerPassword() {
        Customer customer = null;
        String newPassword = "ABCDEDFG";

        try {
            customer = this.customerService.updateCustomerPasswordById(CUSTOMER_KEY, newPassword);
        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }
        assertNotNull(customer);
        assertEquals(customer.getPassword(), newPassword);
    }

    @Test
    public void testUpdateCustomerAddress() {
        Customer savedCustomer = null;

        try {
            savedCustomer = this.customerService.updateCustomerAddressById(CUSTOMER_KEY, ADDRESS_KEY);
        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }
        assertNotNull(savedCustomer);
        assertEquals(CUSTOMER_KEY, savedCustomer.getPersonID());
        assertEquals(FIRST_NAME, savedCustomer.getFirstName());
        assertEquals(LAST_NAME, savedCustomer.getLastName());
        assertEquals(EMAIL, savedCustomer.getEmail());
        assertEquals(PASSWORD, savedCustomer.getPassword());

        assertNotNull(savedCustomer.getAddress());
        assertNotNull(savedCustomer.getAddress());
        assertEquals(savedCustomer.getAddress().getStreetName(), STREET_NAME);
        assertEquals(savedCustomer.getAddress().getStreetNum(), STREET_NUM);
        assertEquals(savedCustomer.getAddress().getCity(), CITY);
        assertEquals(savedCustomer.getAddress().getCountry(), COUNTRY);
        assertEquals(savedCustomer.getAddress().getPostalCode(), POSTAL_CODE);
        assertEquals(savedCustomer.getAddress().isLocal(), IS_LOCAL);
        assertEquals(savedCustomer.getAddress().getAddressID(), ADDRESS_KEY);
    }
    
    @Test
    public void testUpdateCustomerProfile() {
        Customer savedCustomer = createCustomerWithAddress();
        
        try {
            savedCustomer = this.customerService.updateProfile(FIRST_NAME2, LAST_NAME2, EMAIL2, PASSWORD2, CITY, COUNTRY, POSTAL_CODE, STREET_NAME, STREET_NUM, true, CUSTOMER_KEY);
        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }
        assertNotNull(savedCustomer);
        assertEquals(CUSTOMER_KEY, savedCustomer.getPersonID());
        assertEquals(FIRST_NAME2, savedCustomer.getFirstName());
        assertEquals(LAST_NAME2, savedCustomer.getLastName());
        assertEquals(EMAIL2, savedCustomer.getEmail());
        assertEquals(PASSWORD2, savedCustomer.getPassword());

        assertNotNull(savedCustomer.getAddress());
        assertEquals(savedCustomer.getAddress().getStreetName(), STREET_NAME);
        assertEquals(savedCustomer.getAddress().getStreetNum(), STREET_NUM);
        assertEquals(savedCustomer.getAddress().getCity(), CITY);
        assertEquals(savedCustomer.getAddress().getCountry(), COUNTRY);
        assertEquals(savedCustomer.getAddress().getPostalCode(), POSTAL_CODE);
        assertEquals(savedCustomer.getAddress().isLocal(), IS_LOCAL);
    }
    
    @Test
    public void deleteCustomerUnSuccessful() {
        Customer c = createCustomer();
        boolean b = false;
        String error = null;
        try {
             b = customerService.deleteCustomerByID(10);
        } catch (NullPointerException exp) {
            error = exp.getMessage();
        }
        assertFalse(b);
        assertEquals("Cannot find Customer with this personID", error);
    }
}
