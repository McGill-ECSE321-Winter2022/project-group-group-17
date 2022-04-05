package ca.mcgill.ecse321.grocerystoresystem.dto;

import ca.mcgill.ecse321.grocerystoresystem.model.Order;

import java.util.List;

public class CustomerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean loginStatus;
    private List<OrderDto> orders;
    private AddressDto addressDto;

    public CustomerDto(int id, String firstName, String lastName, String email, boolean loginStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        this.setLoginStatus(loginStatus);
    }

    public CustomerDto(int id, String firstName, String lastName, String email, AddressDto addressDto, boolean loginStatus
            , List<OrderDto> orders) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressDto = addressDto;
        this.loginStatus = loginStatus;
        this.id = id;
        this.orders = orders;
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

    public boolean getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }
}
