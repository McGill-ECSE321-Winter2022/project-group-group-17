package ca.mcgill.ecse321.grocerystoresystem.dto;

public class AddressDto {
    private int id;

    private boolean isLocal;
    private String streetName;
    private String streetNum;
    private String city;
    private String postalCode;
    private String country;

    public AddressDto(int id, boolean isLocal, String streetName, String streetNum,
                      String city, String postalCode, String country) {
        this.id = id;
        this.isLocal = isLocal;
        this.streetName = streetName;
        this.streetNum = streetNum;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
