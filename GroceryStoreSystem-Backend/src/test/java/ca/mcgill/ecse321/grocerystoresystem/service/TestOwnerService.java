package ca.mcgill.ecse321.grocerystoresystem.service;

import ca.mcgill.ecse321.grocerystoresystem.dao.AddressRepository;
import ca.mcgill.ecse321.grocerystoresystem.dao.OwnerRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.Owner;
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
public class TestOwnerService {
    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerService ownerService;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private static final int OWNER_KEY = 1001;
    private static final int NO_OWNER_KEY = 234;

    private static final String FIRST_NAME = "Mario";
    private static final String LAST_NAME = "Bouzakhm";
    private static final String EMAIL = "mariobouzakhm02@gmail.com";
    private static final String PASSWORD = "12345";

    private static final int OWNER_KEY_2 = 1002;
    private static final String FIRST_NAME2 = "Maria";
    private static final String LAST_NAME2 = "Bouzakhm";
    private static final String EMAIL2 = "mariabouzakhm02@gmail.com";
    private static final String PASSWORD2 = "12345678";

    private static final int OWNER_KEY_3 = 1003;
    private static final String FIRST_NAME3 = "Mario";
    private static final String LAST_NAME3 = "Bouzakhmm";
    private static final String EMAIL3 = "mario.bouzakhm@gmail.com";
    private static final String PASSWORD3 = "12345678";

    private static final int ADDRESS_KEY = 1001;
    private static final String STREET_NUM = "1239";
    private static final String STREET_NAME = "Peel";
    private static final String CITY = "Montreal";
    private static final String POSTAL_CODE = "HHH HHH";
    private static final String COUNTRY = "Canada";
    private static final boolean IS_LOCAL = true;


    private Owner createOwner() {
        Owner owner = new Owner();
        owner.setPersonID(OWNER_KEY);
        owner.setFirstName(FIRST_NAME);
        owner.setLastName(LAST_NAME);
        owner.setEmail(EMAIL);
        owner.setPassword(PASSWORD);

        return owner;
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

    private List<Owner> createOwners() {
        ArrayList<Owner> owners = new ArrayList<>();

        Owner owner = new Owner();
        owner.setPersonID(OWNER_KEY);
        owner.setFirstName(FIRST_NAME);
        owner.setLastName(LAST_NAME);
        owner.setEmail(EMAIL);
        owner.setPassword(PASSWORD);

        Owner owner2 = new Owner();
        owner2.setPersonID(OWNER_KEY_2);
        owner2.setFirstName(FIRST_NAME2);
        owner2.setLastName(LAST_NAME2);
        owner2.setEmail(EMAIL2);
        owner2.setPassword(PASSWORD2);

        owners.add(owner);
        owners.add(owner2);

        return owners;
    }

    private List<Owner> createOwners2() {
        ArrayList<Owner> owners = new ArrayList<>();

        Owner owner3 = new Owner();
        owner3.setPersonID(OWNER_KEY_3);
        owner3.setFirstName(FIRST_NAME3);
        owner3.setLastName(LAST_NAME3);
        owner3.setEmail(EMAIL3);
        owner3.setPassword(PASSWORD3);

        owners.add(owner3);

        return owners;
    }

    @BeforeEach
    public void setMockOutput() {
        lenient().when(ownerRepository.findOwnerByPersonID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(OWNER_KEY)) {
                return createOwner();
            } else {
                return null;
            }
        });

        lenient().when(ownerRepository.findOwnerByFirstName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(FIRST_NAME)) {
                 List<Owner> owners = createOwners2();
                 owners.add(createOwner());

                 return owners;
            } else {
                return new ArrayList<>();
            }
        });

        lenient().when(ownerRepository.findOwnerByLastName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(LAST_NAME)) {
                return createOwners();

            } else {
                return new ArrayList<>();
            }
        });

        lenient().when(ownerRepository.findOwnerByEmail(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(EMAIL)) {
                return createOwner();
            } else {
                return null;
            }
        });

        lenient().when(ownerRepository.findOwnerByFirstNameAndLastName(anyString(), anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(FIRST_NAME) && invocation.getArgument(1).equals(LAST_NAME)) {
                List<Owner> owners = new ArrayList<>();
                owners.add(createOwner());

                return owners;
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

        lenient().when(ownerRepository.existsByPersonID(anyInt())).thenAnswer(
                (InvocationOnMock invocation) -> invocation.getArgument(0).equals(OWNER_KEY));
        lenient().when(ownerRepository.existsByFirstName(anyString())).thenAnswer(
                (InvocationOnMock invocation) -> invocation.getArgument(0).equals(FIRST_NAME));
        lenient().when(ownerRepository.existsByLastName(anyString())).thenAnswer(
                (InvocationOnMock invocation) -> invocation.getArgument(0).equals(LAST_NAME));
        lenient().when(ownerRepository.existsByEmail(anyString())).thenAnswer(
                (InvocationOnMock invocation) -> invocation.getArgument(0).equals(EMAIL));
        lenient().when(ownerRepository.existsByFirstNameAndLastName(anyString(), anyString())).thenAnswer( (InvocationOnMock invocation)
                -> (invocation.getArgument(0).equals(FIRST_NAME) && invocation.getArgument(1).equals(LAST_NAME)));

        lenient().when(ownerRepository.findAll()).thenAnswer( (InvocationOnMock invocation) -> createOwners2());


        Answer<?> returnParamAsAnswer = (InvocationOnMock invocation) -> {return invocation.getArgument(0);};
        lenient().when(ownerRepository.save(any(Owner.class))).thenAnswer(returnParamAsAnswer);
    }

    @Test
    public void testCreateOwner() {
        Owner savedOwner = this.ownerService.createOwner();

        assertNotNull(savedOwner);
        assertNull(savedOwner.getFirstName());
        assertNull(savedOwner.getLastName());
        assertNull(savedOwner.getEmail());
        assertNull(savedOwner.getPassword());
        assertEquals(savedOwner.getPersonID(), 0);
    }

    @Test
    public void testCreateOwner2() {
        Owner savedOwner = null;

        try {
            savedOwner = this.ownerService.createOwner(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        }
        catch(IllegalArgumentException exp) {
            fail(exp.getMessage());
        }
        assertNotNull(savedOwner);
        assertEquals(savedOwner.getFirstName(), FIRST_NAME);
        assertEquals(savedOwner.getLastName(), LAST_NAME);
        assertEquals(savedOwner.getEmail(), EMAIL);
        assertEquals(savedOwner.getPassword(), PASSWORD);
    }

    @Test
    public void testCreateOwner3() {
        Owner savedOwner = null;

        try {
            savedOwner = this.ownerService.createOwner(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, createAddress());
        }
        catch(IllegalArgumentException exp) {
            fail(exp.getMessage());
        }
        assertNotNull(savedOwner);
        assertEquals(savedOwner.getFirstName(), FIRST_NAME);
        assertEquals(savedOwner.getLastName(), LAST_NAME);
        assertEquals(savedOwner.getEmail(), EMAIL);
        assertEquals(savedOwner.getPassword(), PASSWORD);

        assertNotNull(savedOwner.getAddress());
        assertEquals(savedOwner.getAddress().getStreetName(), STREET_NAME);
        assertEquals(savedOwner.getAddress().getStreetNum(), STREET_NUM);
        assertEquals(savedOwner.getAddress().getCity(), CITY);
        assertEquals(savedOwner.getAddress().getCountry(), COUNTRY);
        assertEquals(savedOwner.getAddress().getPostalCode(), POSTAL_CODE);
        assertEquals(savedOwner.getAddress().isLocal(), IS_LOCAL);
        assertEquals(savedOwner.getAddress().getAddressID(), ADDRESS_KEY);
    }

    @Test
    public void testCreateOwnerFail1() {
        Owner savedOwner = null;
        String error =  "";

        try {
            savedOwner = this.ownerService.createOwner(null, LAST_NAME, EMAIL, PASSWORD);
        }
        catch(IllegalArgumentException exp) {
            error = exp.getMessage();
        }
        assertNull(savedOwner);
        assertEquals(error, "Please provide valid arguments: Invalid First Name");
    }

    @Test
    public void testCreateOwnerFail2() {
        Owner savedOwner = null;
        String error =  "";

        try {
            savedOwner = this.ownerService.createOwner(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, null);
        }
        catch(IllegalArgumentException exp) {
            error = exp.getMessage();
        }
        assertNull(savedOwner);
        assertEquals(error, "Please provide valid arguments: Address");
    }

    @Test
    public void testGetOwnerByIDSuccessful() {
        assertEquals(1, ownerService.getAllOwners().size());
        Owner owner = null;
        try {
            owner = ownerService.findOwnerByID(OWNER_KEY);
        }
        catch(NullPointerException exp) {
            fail(exp.getMessage());
        }

        assertNotNull(owner);
        assertEquals(OWNER_KEY, owner.getPersonID());
        assertEquals(FIRST_NAME, owner.getFirstName());
        assertEquals(LAST_NAME, owner.getLastName());
        assertEquals(EMAIL, owner.getEmail());
        assertEquals(PASSWORD, owner.getPassword());
    }

    @Test
    public void testGetOwnerByIDUnsuccessful() {
        assertEquals(1, ownerService.getAllOwners().size());
        Owner owner = null;
        String error =  "";
        try {
            owner = ownerService.findOwnerByID(NO_OWNER_KEY);
        }
        catch(NullPointerException exp) {
            error = exp.getMessage();
        }

        assertNull(owner);

        assertEquals("Owner not found", error);
    }

    @Test
    public void testGetOwnerByFirstNameSuccessful() {
        assertEquals(1, ownerService.getAllOwners().size());
        List<Owner> owners = null;
        try {
            owners = this.ownerService.findOwnerByFirstName("Mario");
        }
        catch(NullPointerException exp) {
            fail(exp.getMessage());
        }

        assertEquals(owners.size(), 2);
        assertNotNull(owners);
    }

    @Test
    public void testGetOwnerByFirstNameUnsuccessful() {
        List<Owner> owners = null;
        String error =  "";
        try {
            owners = this.ownerService.findOwnerByFirstName("Marioo");
        }
        catch(NullPointerException exp) {
            error = exp.getMessage();
        }

        assertNull(owners);
        assertEquals("No owners found", error);
    }

    @Test
    public void testGetOwnerByLastNameSuccessful() {
        List<Owner> owners = null;
        try {
            owners = this.ownerService.findOwnerByLastName(LAST_NAME);
        }
        catch(NullPointerException exp) {
            fail(exp.getMessage());
        }

        assertEquals(owners.size(), 2);
        assertNotNull(owners);
    }

    @Test
    public void testGetOwnerByLastNameUnsuccessful() {
        List<Owner> owners = null;
        String error =  "";
        try {
            owners = this.ownerService.findOwnerByLastName("helloworld");
        }
        catch(NullPointerException exp) {
            error = exp.getMessage();
        }

        assertNull(owners);
        assertEquals("No owners found", error);
    }

    @Test
    public void testGetOwnerByEmailSuccessful() {
        Owner owner = null;
        try {
            owner  = this.ownerService.findOwnerByEmail(EMAIL);
        }
        catch(NullPointerException exp) {
            fail(exp.getMessage());
        }

        assertNotNull(owner);
        assertEquals(OWNER_KEY, owner.getPersonID());
        assertEquals(FIRST_NAME, owner.getFirstName());
        assertEquals(LAST_NAME, owner.getLastName());
        assertEquals(EMAIL, owner.getEmail());
        assertEquals(PASSWORD, owner.getPassword());
    }

    @Test
    public void testGetOwnerByFullNameSuccessful() {
        List<Owner> owners = null;
        try {
            owners = this.ownerService.findOwnerByName(FIRST_NAME, LAST_NAME);
        }
        catch(NullPointerException exp) {
            fail(exp.getMessage());
        }

        assertEquals(owners.size(), 1);
        Owner owner = owners.get(0);

        assertNotNull(owner);
        assertEquals(OWNER_KEY, owner.getPersonID());
        assertEquals(FIRST_NAME, owner.getFirstName());
        assertEquals(LAST_NAME, owner.getLastName());
        assertEquals(EMAIL, owner.getEmail());
        assertEquals(PASSWORD, owner.getPassword());
    }

    @Test
    public void testGetOwnerByEmailUnsuccessful() {
        Owner owner = null;
        String error =  "";
        try {
            owner = this.ownerService.findOwnerByEmail("email");
        }
        catch(NullPointerException exp) {
            error = exp.getMessage();
        }

        assertNull(owner);
        assertEquals("No owners found", error);
    }

    @Test
    public void testGetOwnerByFullNameUnsuccessful() {
        List<Owner> owners = null;
        String error =  "";
        try {
            owners = this.ownerService.findOwnerByName("M", "M");
        }
        catch(NullPointerException exp) {
            error = exp.getMessage();
        }

        assertNull(owners);
        assertEquals("No owners found", error);
    }

    @Test
    public void testGetAllOwners() {
        List<Owner> owners = this.ownerService.getAllOwners();
        Owner owner = null;

        assertEquals(owners.size(), 1);
        owner = owners.get(0);


        assertEquals(owner.getPersonID(), 1003);
        assertEquals(owner.getFirstName(), FIRST_NAME3);
        assertEquals(owner.getLastName(), LAST_NAME3);
        assertEquals(owner.getEmail(), EMAIL3);
        assertEquals(owner.getPassword(), PASSWORD3);
    }

    @Test
    public void testIsOwnerByID() {
        boolean result = this.ownerService.isOwnerByID(OWNER_KEY);

        assertTrue(result);
    }

    @Test
    public void testIsOwnerByFirstName() {
        boolean result = this.ownerService.isOwnerByFirstName(FIRST_NAME);

        assertTrue(result);
    }

    @Test
    public void testIsOwnerByLastName() {
        boolean result = this.ownerService.isOwnerByLastName(LAST_NAME);

        assertTrue(result);
    }

    @Test
    public void testIsOwnerByEmail() {
        boolean result = this.ownerService.isOwnerByEmail(EMAIL);

        assertTrue(result);
    }

    @Test
    public void testIsOwnerByFirstAndLastName() {
        boolean result = this.ownerService.isOwnerByFirstAndLastName(FIRST_NAME, LAST_NAME);

        assertTrue(result);
    }

    @Test
    public void testIsOwnerByIDFail() {
        boolean result = this.ownerService.isOwnerByID(1);

        assertFalse(result);
    }

    @Test
    public void testIsOwnerByFirstNameFail() {
        boolean result = this.ownerService.isOwnerByFirstName("M");

        assertFalse(result);
    }

    @Test
    public void testIsOwnerByLastNameFail() {
        boolean result = this.ownerService.isOwnerByLastName("M");

        assertFalse(result);
    }

    @Test
    public void testIsOwnerByEmailFail() {
        boolean result = this.ownerService.isOwnerByEmail("M");

        assertFalse(result);
    }

    @Test
    public void testIsOwnerByFirstNameAndLastNameFail() {
        boolean result = this.ownerService.isOwnerByFirstAndLastName("M", "M");

        assertFalse(result);
    }

    @Test
    public void testUpdateOwnerPassword() {
        Owner owner = null;
        String newPassword = "ABCDEDFG";

        try {
            owner = this.ownerService.updateOwnerPasswordById(OWNER_KEY, newPassword);
        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }

        assertNotNull(owner);
        assertEquals(owner.getPassword(), newPassword);
    }

    @Test
    public void testUpdateOwnerAddress() {
        Owner savedOwner = null;

        try {
            savedOwner = this.ownerService.updateOwnerAddressByID(OWNER_KEY, ADDRESS_KEY);

        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }

        assertNotNull(savedOwner);
        assertEquals(OWNER_KEY, savedOwner.getPersonID());
        assertEquals(FIRST_NAME, savedOwner.getFirstName());
        assertEquals(LAST_NAME, savedOwner.getLastName());
        assertEquals(EMAIL, savedOwner.getEmail());
        assertEquals(PASSWORD, savedOwner.getPassword());

        assertNotNull(savedOwner.getAddress());
        assertNotNull(savedOwner.getAddress());
        assertEquals(savedOwner.getAddress().getStreetName(), STREET_NAME);
        assertEquals(savedOwner.getAddress().getStreetNum(), STREET_NUM);
        assertEquals(savedOwner.getAddress().getCity(), CITY);
        assertEquals(savedOwner.getAddress().getCountry(), COUNTRY);
        assertEquals(savedOwner.getAddress().getPostalCode(), POSTAL_CODE);
        assertEquals(savedOwner.getAddress().isLocal(), IS_LOCAL);
        assertEquals(savedOwner.getAddress().getAddressID(), ADDRESS_KEY);

    }
}
