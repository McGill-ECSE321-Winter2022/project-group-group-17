package ca.mcgill.ecse321.grocerystoresystem.dto;

public class OwnerDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private AddressDto addressDto;

    public OwnerDto(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public OwnerDto(String firstName, String lastName, String email, String password, AddressDto addressDto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.addressDto = addressDto;
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
}
