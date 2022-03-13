
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
    private static final String EMAIL = "amy.wang@mail.mcgill.ca";
    private static final String PASSWORD = "1234";


    private static final int EMP_KEY_2 = 1002;
    private static final String FIRST_NAME2 = "Maria";
    private static final String LAST_NAME2 = "Bouzakhm";
    private static final String EMAIL2 = "mariabouzakhm02@gmail.com";
    private static final String PASSWORD2 = "12345678";

    private static final int EMP_KEY_3 = 1003;
    private static final String FIRST_NAME3 = "Mario";
    private static final String LAST_NAME3 = "Bouzakhmm";
    private static final String EMAIL3 = "mario.bouzakhm@gmail.com";
    private static final String PASSWORD3 = "12345678";

    private static final int ADDRESS_KEY = 1001;
    private static final String STREET_NUM = "1450";
    private static final String STREET_NAME = "Guy";
    private static final String CITY = "Montreal";
    private static final String POSTAL_CODE = "HHH HHH";
    private static final String COUNTRY = "Canada";
    private static final boolean IS_LOCAL = true;
    private static final boolean IS_LOGGED = true;

    private Employee createEmployee() {
        Employee employee = new Employee();
        employee.setPersonID(EMPLOYEE_KEY);
        employee.setFirstName(FIRST_NAME);
        employee.setLastName(LAST_NAME);
        employee.setEmail(EMAIL);
        employee.setPassword(PASSWORD);

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
        employee.setEmpStatus(EmployeeStatus.hired);

        Employee employee2 = new Employee();
        employee2.setPersonID(EMP_KEY_2);
        employee2.setFirstName(FIRST_NAME2);
        employee2.setLastName(LAST_NAME2);
        employee2.setEmail(EMAIL2);
        employee2.setPassword(PASSWORD2);
        employee.setLoginStatus(IS_LOGGED);
        employee.setEmpStatus(EmployeeStatus.hired);

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









}
