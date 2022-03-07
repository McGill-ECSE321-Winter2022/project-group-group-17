package ca.mcgill.ecse321.grocerystoresystem.dto;

import ca.mcgill.ecse321.grocerystoresystem.model.EmployeeStatus;
import ca.mcgill.ecse321.grocerystoresystem.model.ShiftStatus;

public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private EmployeeStatus status;

    private AddressDto addressDto;

    public EmployeeDto(String firstName, String lastName, String email, String password, EmployeeStatus status, AddressDto addressDto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.addressDto = addressDto;
    }

    public EmployeeDto(String firstName, String lastName, String email, String password, EmployeeStatus status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }
}
