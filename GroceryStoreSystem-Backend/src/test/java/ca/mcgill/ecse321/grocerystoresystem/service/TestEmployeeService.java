
package ca.mcgill.ecse321.grocerystoresystem.service;

import ca.mcgill.ecse321.grocerystoresystem.dao.AddressRepository;
import ca.mcgill.ecse321.grocerystoresystem.dao.EmployeeRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.Employee;
import ca.mcgill.ecse321.grocerystoresystem.model.EmployeeStatus;
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
public class TestEmployeeService{

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private static final int EMPLOYEE_KEY = 1010;
    private static final int NO_EMPLOYEE_KEY = 123;

    private static final String FIRST_NAME = "Amy";
    private static final String LAST_NAME = "Wang";
    private static final String EMAIL = "amywang@mail.mcgill.ca";
    private static final String PASSWORD = "12345678";


    private static final int EMP_KEY_2 = 1002;
    private static final String FIRST_NAME2 = "Qi";
    private static final String LAST_NAME2 = "Hao";
    private static final String EMAIL2 = "qihao@gmail.com";
    private static final String PASSWORD2 = "abcdefgh";

    private static final int EMP_KEY_3 = 1003;
    private static final String FIRST_NAME3 = "Anna";
    private static final String LAST_NAME3 = "Chen";
    private static final String EMAIL3 = "annachen@outlook.com";
    private static final String PASSWORD3 = "bubbles123";

    private static final int EMP_KEY_4 = 1004;
    private static final String FIRST_NAME4 = "Ryan";
    private static final String LAST_NAME4 = "Li";
    private static final String EMAIL4 = "ryli@hotmail.com";
    private static final String PASSWORD4 = "pink12345";

    private static final int ADDRESS_KEY = 1001;
    private static final String STREET_NUM = "1450";
    private static final String STREET_NAME = "Guy";
    private static final String CITY = "Montreal";
    private static final String POSTAL_CODE = "HHH HHH";
    private static final String COUNTRY = "Canada";
    private static final boolean IS_LOCAL = true;
    private static final boolean IS_LOGGED = true;
    private static final EmployeeStatus HIRED_STATUS = EmployeeStatus.hired;
    //private static final EmployeeStatus FIRED_STATUS = EmployeeStatus.fired;
    //private static final EmployeeStatus RESIGNED_STATUS = EmployeeStatus.resigned; 

    private Employee createEmployee() {
        Employee employee = new Employee();
        employee.setPersonID(EMPLOYEE_KEY);
        employee.setFirstName(FIRST_NAME);
        employee.setLastName(LAST_NAME);
        employee.setEmail(EMAIL);
        employee.setPassword(PASSWORD);
        employee.setEmpStatus(HIRED_STATUS);

        return employee;
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

    private List<Employee> createEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();

        Employee employee = new Employee();
        employee.setPersonID(EMPLOYEE_KEY);
        employee.setFirstName(FIRST_NAME);
        employee.setLastName(LAST_NAME);
        employee.setEmail(EMAIL);
        employee.setPassword(PASSWORD);
        employee.setLoginStatus(IS_LOGGED);
        employee.setEmpStatus(HIRED_STATUS);

        Employee employee2 = new Employee();
        employee2.setPersonID(EMP_KEY_2);
        employee2.setFirstName(FIRST_NAME2);
        employee2.setLastName(LAST_NAME2);
        employee2.setEmail(EMAIL2);
        employee2.setPassword(PASSWORD2);
        employee.setLoginStatus(IS_LOGGED);
        employee.setEmpStatus(HIRED_STATUS);

        employees.add(employee);
        employees.add(employee2);

        return employees;
    }

    private List<Employee> createEmployees2() {
        ArrayList<Employee> employees = new ArrayList<>();

        Employee employee3 = new Employee();
        employee3.setPersonID(EMP_KEY_3);
        employee3.setFirstName(FIRST_NAME3);
        employee3.setLastName(LAST_NAME3);
        employee3.setEmail(EMAIL3);
        employee3.setPassword(PASSWORD3);
        employee3.setEmpStatus(HIRED_STATUS);

        employees.add(employee3);

        return employees;
    }

    @BeforeEach
    public void setMockOutput() {
        lenient().when(employeeRepository.findEmployeeByPersonID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(EMPLOYEE_KEY)) {
                return createEmployee();
            } else {
                return null;
            }
        });

        lenient().when(employeeRepository.findEmployeeByFirstName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(FIRST_NAME)) {
                 List<Employee> employees = new ArrayList<>();
                 employees.add(createEmployee());

                 return employees;
            } else {
                return new ArrayList<>();
            }
        });

        lenient().when(employeeRepository.findEmployeeByLastName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(LAST_NAME)) {
                return createEmployees();

            } else {
                return new ArrayList<>();
            }
        });

        lenient().when(employeeRepository.findEmployeeByEmail(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(EMAIL)) {
                return createEmployee();
            } else {
                return null;
            }
        });

        lenient().when(employeeRepository.findEmployeeByFirstNameAndLastName(anyString(), anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(FIRST_NAME) && invocation.getArgument(1).equals(LAST_NAME)) {
                List<Employee> employees = new ArrayList<>();
                employees.add(createEmployee());

                return employees;
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

        lenient().when(employeeRepository.existsByPersonID(anyInt())).thenAnswer(
            (InvocationOnMock invocation) -> invocation.getArgument(0).equals(EMPLOYEE_KEY));
    lenient().when(employeeRepository.existsByFirstName(anyString())).thenAnswer(
            (InvocationOnMock invocation) -> invocation.getArgument(0).equals(FIRST_NAME));
    lenient().when(employeeRepository.existsByLastName(anyString())).thenAnswer(
            (InvocationOnMock invocation) -> invocation.getArgument(0).equals(LAST_NAME));
    lenient().when(employeeRepository.existsByEmail(anyString())).thenAnswer(
            (InvocationOnMock invocation) -> invocation.getArgument(0).equals(EMAIL));
    lenient().when(employeeRepository.existsByFirstNameAndLastName(anyString(), anyString())).thenAnswer( (InvocationOnMock invocation)
            -> (invocation.getArgument(0).equals(FIRST_NAME) && invocation.getArgument(1).equals(LAST_NAME)));

    lenient().when(employeeRepository.findAll()).thenAnswer( (InvocationOnMock invocation) -> createEmployees2());


    Answer<?> returnParamAsAnswer = (InvocationOnMock invocation) -> {return invocation.getArgument(0);};
    lenient().when(employeeRepository.save(any(Employee.class))).thenAnswer(returnParamAsAnswer);

}

   @Test
    public void testCreateEmployee() {
        Employee savedEmployee = this.employeeService.createEmployee();

        assertNotNull(savedEmployee);
        assertNull(savedEmployee.getFirstName());
        assertNull(savedEmployee.getLastName());
        assertNull(savedEmployee.getEmail());
        assertNull(savedEmployee.getPassword());
        assertEquals(savedEmployee.getPersonID(), 0);
        assertNull(savedEmployee.getEmpStatus());
    }


//    @Test
//    public void testCreateEmployee2() {
//        Employee savedEmployee = null;
//
//        try {
//            savedEmployee = this.employeeService.createEmployee(FIRST_NAME4, LAST_NAME4, EMAIL4, PASSWORD4, HIRED_STATUS);
//        }
//        catch(IllegalArgumentException exp) {
//            fail(exp.getMessage());
//        }
//        assertNotNull(savedEmployee);
//        assertEquals(savedEmployee.getFirstName(), FIRST_NAME4);
//        assertEquals(savedEmployee.getLastName(), LAST_NAME4);
//        assertEquals(savedEmployee.getEmail(), EMAIL4);
//        assertEquals(savedEmployee.getPassword(), PASSWORD4);
//        assertEquals(savedEmployee.getEmpStatus(), HIRED_STATUS);
//    }

//    @Test
//    public void testCreateEmployee3() {
//        Employee savedEmployee = null;
//
//        try {
//            savedEmployee = this.employeeService.createEmployee(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, HIRED_STATUS, createAddress());
//        }
//        catch(IllegalArgumentException exp) {
//            fail(exp.getMessage());
//        }
//        assertNotNull(savedEmployee);
//        assertEquals(savedEmployee.getFirstName(), FIRST_NAME);
//        assertEquals(savedEmployee.getLastName(), LAST_NAME);
//        assertEquals(savedEmployee.getEmail(), EMAIL);
//        assertEquals(savedEmployee.getPassword(), PASSWORD);
//        assertEquals(savedEmployee.getEmpStatus(), HIRED_STATUS);
//
//        assertNotNull(savedEmployee.getAddress());
//        assertEquals(savedEmployee.getAddress().getStreetName(), STREET_NAME);
//        assertEquals(savedEmployee.getAddress().getStreetNum(), STREET_NUM);
//        assertEquals(savedEmployee.getAddress().getCity(), CITY);
//        assertEquals(savedEmployee.getAddress().getCountry(), COUNTRY);
//        assertEquals(savedEmployee.getAddress().getPostalCode(), POSTAL_CODE);
//        assertEquals(savedEmployee.getAddress().isLocal(), IS_LOCAL);
//        assertEquals(savedEmployee.getAddress().getAddressID(), ADDRESS_KEY);
//    }

    @Test
    public void testCreateEmployeeFail1() {
        Employee savedEmployee = null;
        String error =  "";

        try {
            savedEmployee = this.employeeService.createEmployee(null, LAST_NAME, EMAIL, PASSWORD, HIRED_STATUS);
        }
        catch(IllegalArgumentException exp) {
            error = exp.getMessage();
        }
        assertNull(savedEmployee);
        assertEquals(error, "Please enter a valid name");
    }

    @Test
    public void testCreateEmployeeFail2() {
        Employee savedEmployee = null;
        String error =  "";

        try {
            savedEmployee = this.employeeService.createEmployee(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, null);
        }
        catch(IllegalArgumentException exp) {
            error = exp.getMessage();
        }
        assertNull(savedEmployee);
        assertEquals(error, "Must input employee status to create employee account");
    }

    @Test
    public void testCreateEmployeeFail3() {
        Employee savedEmployee = null;
        String error =  "";

        try {
            savedEmployee = this.employeeService.createEmployee(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, HIRED_STATUS, null);
        }
        catch(IllegalArgumentException exp) {
            error = exp.getMessage();
        }
        assertNull(savedEmployee);
        assertEquals(error, "Must input a valid address");
    }

    @Test
    public void testGetEmployeeByIDSuccessful() {
        assertEquals(1, employeeService.getAllEmployees().size());
        Employee employee = null;
        try {
            employee = employeeService.findEmployeeByID(EMPLOYEE_KEY);
        }
        catch(NullPointerException exp) {
            fail(exp.getMessage());
        }

        assertNotNull(employee);
        assertEquals(EMPLOYEE_KEY, employee.getPersonID());
        assertEquals(FIRST_NAME, employee.getFirstName());
        assertEquals(LAST_NAME, employee.getLastName());
        assertEquals(EMAIL, employee.getEmail());
        assertEquals(PASSWORD, employee.getPassword());
        assertEquals(HIRED_STATUS, employee.getEmpStatus());
    }

    @Test
    public void testGetEmployeeByIDUnsuccessful() {
        assertEquals(1, employeeService.getAllEmployees().size());
        Employee employee = null;
        String error =  "";
        try {
            employee = employeeService.findEmployeeByID(NO_EMPLOYEE_KEY);
        }
        catch(NullPointerException exp) {
            error = exp.getMessage();
        }

        assertNull(employee);

        assertEquals("Employee not found", error);
    }

    @Test
    public void testGetEmployeeByFirstNameSuccessful() {
        assertEquals(1, employeeService.getAllEmployees().size());
        List<Employee> employees = null;
        try {
            employees = this.employeeService.findEmployeeByFirstName("Amy");
        }
        catch(NullPointerException exp) {
            fail(exp.getMessage());
        }

        assertEquals(employees.size(), 1);
        assertNotNull(employees);
    }

    @Test
    public void testGetEmployeeByFirstNameUnsuccessful() {
        List<Employee> employees = null;
        String error =  "";
        try {
            employees = this.employeeService.findEmployeeByFirstName("Amyy");
        }
        catch(NullPointerException exp) {
            error = exp.getMessage();
        }

        assertNull(employees);
        assertEquals("No employees found", error);
    }

    @Test
    public void testGetEmployeeByLastNameSuccessful() {
        List<Employee> employees = null;
        try {
            employees = this.employeeService.findEmployeeByLastName(LAST_NAME);
        }
        catch(NullPointerException exp) {
            fail(exp.getMessage());
        }

        assertEquals(employees.size(), 2);
        assertNotNull(employees);
    }

    @Test
    public void testGetEmployeeByLastNameUnsuccessful() {
        List<Employee> employees = null;
        String error =  "";
        try {
            employees = this.employeeService.findEmployeeByLastName("helloworld");
        }
        catch(NullPointerException exp) {
            error = exp.getMessage();
        }

        assertNull(employees);
        assertEquals("No employees found", error);
    }

    @Test
    public void testGetEmployeeByEmailSuccessful() {
        Employee employee = null;
        try {
            employee  = this.employeeService.findEmployeeByEmail(EMAIL);
        }
        catch(NullPointerException exp) {
            fail(exp.getMessage());
        }

        assertNotNull(employee);
        assertEquals(EMPLOYEE_KEY, employee.getPersonID());
        assertEquals(FIRST_NAME, employee.getFirstName());
        assertEquals(LAST_NAME, employee.getLastName());
        assertEquals(EMAIL, employee.getEmail());
        assertEquals(PASSWORD, employee.getPassword());
        assertEquals(HIRED_STATUS, employee.getEmpStatus());
    }

    @Test
    public void testGetEmployeeByFullNameSuccessful() {
        List<Employee> employees = null;
        try {
            employees = this.employeeService.findEmployeeByName(FIRST_NAME, LAST_NAME);
        }
        catch(NullPointerException exp) {
            fail(exp.getMessage());
        }

        assertEquals(employees.size(), 1);
        Employee employee = employees.get(0);

        assertNotNull(employee);
        assertEquals(EMPLOYEE_KEY, employee.getPersonID());
        assertEquals(FIRST_NAME, employee.getFirstName());
        assertEquals(LAST_NAME, employee.getLastName());
        assertEquals(EMAIL, employee.getEmail());
        assertEquals(PASSWORD, employee.getPassword());
        assertEquals(HIRED_STATUS, employee.getEmpStatus());
    }

    @Test
    public void testGetEmployeeByEmailUnsuccessful() {
        Employee employee = null;
        String error =  "";
        try {
            employee = this.employeeService.findEmployeeByEmail("email");
        }
        catch(NullPointerException exp) {
            error = exp.getMessage();
        }

        assertNull(employee);
        assertEquals("Employee not found", error);
    }

    @Test
    public void testGetEmployeeByFullNameUnsuccessful() {
        List<Employee> employees = null;
        String error =  "";
        try {
            employees = this.employeeService.findEmployeeByName("A", "A");
        }
        catch(NullPointerException exp) {
            error = exp.getMessage();
        }

        assertNull(employees);
        assertEquals("No employees found", error);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = this.employeeService.getAllEmployees();
        Employee employee = null;

        assertEquals(employees.size(), 1);
        employee = employees.get(0);


        assertEquals(employee.getPersonID(), 1003);
        assertEquals(employee.getFirstName(), FIRST_NAME3);
        assertEquals(employee.getLastName(), LAST_NAME3);
        assertEquals(employee.getEmail(), EMAIL3);
        assertEquals(employee.getPassword(), PASSWORD3);
        assertEquals(employee.getEmpStatus(), HIRED_STATUS);
    }

    @Test
    public void testExistsByID() {
        boolean result = this.employeeService.existsbyID(EMPLOYEE_KEY);

        assertTrue(result);
    }

    @Test
    public void testIsEmployeeByFirstName() {
        boolean result = this.employeeService.isEmployeeByFirstName(FIRST_NAME);

        assertTrue(result);
    }

    @Test
    public void testIsEmployeeByLastName() {
        boolean result = this.employeeService.isEmployeeByLastName(LAST_NAME);

        assertTrue(result);
    }

    @Test
    public void testIsEmployeeByEmail() {
        boolean result = this.employeeService.isEmployeeByEmail(EMAIL);

        assertTrue(result);
    }

    @Test
    public void testIsEmployeeByFirstAndLastName() {
        boolean result = this.employeeService.isEmployeeByFirstAndLastName(FIRST_NAME, LAST_NAME);

        assertTrue(result);
    }

    @Test
    public void testExistsByIDFail() {
        boolean result = this.employeeService.existsbyID(1);

        assertFalse(result);
    }

    @Test
    public void testIsEmployeeByFirstNameFail() {
        boolean result = this.employeeService.isEmployeeByFirstName("M");

        assertFalse(result);
    }

    @Test
    public void testIsEmployeeByLastNameFail() {
        boolean result = this.employeeService.isEmployeeByLastName("M");

        assertFalse(result);
    }

    @Test
    public void testIsEmployeeByEmailFail() {
        boolean result = this.employeeService.isEmployeeByEmail("M");

        assertFalse(result);
    }

    @Test
    public void testIsEmployeeByFirstNameAndLastNameFail() {
        boolean result = this.employeeService.isEmployeeByFirstAndLastName("M", "M");

        assertFalse(result);
    }

    @Test
    public void testUpdateEmployeePassword() {
        Employee employee = null;
        String newPassword = "ABCDEDFG";

        try {
            employee = this.employeeService.updateEmployeePasswordById(EMPLOYEE_KEY, newPassword);
        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }

        assertNotNull(employee);
        assertEquals(employee.getPassword(), newPassword);
    }

    @Test
    public void testUpdateEmployeeAddressById() {
        Employee savedEmployee = null;

        try {
            savedEmployee = this.employeeService.updateEmployeeAddressByID(EMPLOYEE_KEY, ADDRESS_KEY);

        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }

        assertNotNull(savedEmployee);
        assertEquals(EMPLOYEE_KEY, savedEmployee.getPersonID());
        assertEquals(FIRST_NAME, savedEmployee.getFirstName());
        assertEquals(LAST_NAME, savedEmployee.getLastName());
        assertEquals(EMAIL, savedEmployee.getEmail());
        assertEquals(PASSWORD, savedEmployee.getPassword());

        assertNotNull(savedEmployee.getAddress());
        assertNotNull(savedEmployee.getAddress());
        assertEquals(savedEmployee.getAddress().getStreetName(), STREET_NAME);
        assertEquals(savedEmployee.getAddress().getStreetNum(), STREET_NUM);
        assertEquals(savedEmployee.getAddress().getCity(), CITY);
        assertEquals(savedEmployee.getAddress().getCountry(), COUNTRY);
        assertEquals(savedEmployee.getAddress().getPostalCode(), POSTAL_CODE);
        assertEquals(savedEmployee.getAddress().isLocal(), IS_LOCAL);
        assertEquals(savedEmployee.getAddress().getAddressID(), ADDRESS_KEY);

    }

    @Test
    public void testUpdateEmployeePasswordByEmail() {
        Employee e = null;
        String newPassword = "ABCDEDFG";

        try {
            e = this.employeeService.updatePasswordByEmail(EMAIL, PASSWORD, newPassword);
        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }

        assertNotNull(e);
        assertEquals(e.getPassword(), newPassword);
    }

    @Test
    public void testUpdateEmployeeAddressByEmail() {
       Employee savedEmployee = null;

        try {
            savedEmployee = this.employeeService.updateEmployeeAddressByEmail(EMAIL, ADDRESS_KEY);

        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }

        assertNotNull(savedEmployee);
        assertEquals(EMPLOYEE_KEY, savedEmployee.getPersonID());
        assertEquals(FIRST_NAME, savedEmployee.getFirstName());
        assertEquals(LAST_NAME, savedEmployee.getLastName());
        assertEquals(EMAIL, savedEmployee.getEmail());
        assertEquals(PASSWORD, savedEmployee.getPassword());
        assertEquals(HIRED_STATUS, savedEmployee.getEmpStatus());

        assertNotNull(savedEmployee.getAddress());
        assertNotNull(savedEmployee.getAddress());
        assertEquals(savedEmployee.getAddress().getStreetName(), STREET_NAME);
        assertEquals(savedEmployee.getAddress().getStreetNum(), STREET_NUM);
        assertEquals(savedEmployee.getAddress().getCity(), CITY);
        assertEquals(savedEmployee.getAddress().getCountry(), COUNTRY);
        assertEquals(savedEmployee.getAddress().getPostalCode(), POSTAL_CODE);
        assertEquals(savedEmployee.getAddress().isLocal(), IS_LOCAL);
        assertEquals(savedEmployee.getAddress().getAddressID(), ADDRESS_KEY);

    }
    //Delete employee
    //login (email and pass)
    //logout (email)
    //fire, resign, rehire (still need to be added in controller)








}
