package ca.mcgill.ecse321.grocerystoresystem.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Address")
public class Address {
	
	@Id
	@GeneratedValue
	private int addressID;

	@OneToMany(targetEntity = Person.class, fetch = FetchType.EAGER)
	private List<Person> persons;

	@OneToMany(targetEntity = DeliveryOrder.class)
	private List<DeliveryOrder> orders;
	
	private boolean isLocal;

	private String streetName;
	private String streetNum;
	
	private String city;
	private String postalCode;
	
	private String country;
	
	public Address() {};
	
	public Address(String streetName, String streetNum, String city, String postalCode, String country, boolean isLocal) {
		this.streetName = streetName;
		this.streetNum = streetNum;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
		this.isLocal = isLocal;
	}
	
	public Address(int addressID, String streetName, String streetNum, String city, String postalCode, String country, boolean isLocal) {
		this.addressID = addressID;
	    this.streetName = streetName;
		this.streetNum = streetNum;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
		this.isLocal = isLocal;
	}
	public boolean isLocal() {
		return isLocal;
	}

	public void setLocal(boolean isLocal) {
		this.isLocal = isLocal;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNum() {
		return streetNum;
	}

	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getAddressID() {
		return addressID;
	}
	
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public List<Person> getAssociatedPersons() {
		return this.persons;
	}
}
