package ca.mcgill.ecse321.grocerystoresystem.dto;

import java.util.List;

public class OwnerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<OrderDto> orders;
    private AddressDto addressDto;

    public OwnerDto(int id, String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
    }

    public OwnerDto(int id, String firstName, String lastName, String email, AddressDto addressDto, List<OrderDto> orders) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressDto = addressDto;
        this.orders = orders;
        this.id = id;
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

    public List<OrderDto> getOrders() {
        return orders;
    }
}
