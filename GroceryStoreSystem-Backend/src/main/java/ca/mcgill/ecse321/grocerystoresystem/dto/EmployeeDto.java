package ca.mcgill.ecse321.grocerystoresystem.dto;

import ca.mcgill.ecse321.grocerystoresystem.model.EmployeeStatus;

public class EmployeeDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    private EmployeeStatus status;

    private AddressDto addressDto;

    public EmployeeDto(int id, String firstName, String lastName, String email, EmployeeStatus status, AddressDto addressDto) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.addressDto = addressDto;
    }

    public EmployeeDto(int id, String firstName, String lastName, String email, EmployeeStatus status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
