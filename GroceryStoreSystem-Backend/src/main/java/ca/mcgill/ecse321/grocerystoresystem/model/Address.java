package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {
	
	@Id
	@GeneratedValue
	private int address_ID;
	
	private String streetName;
	private String streetNum;
	
	private String city;
	private String postalCode;
	
	private String country;
	
	public Address(String streetName, String streetNum, String city, String postalCode, String country) {
		this.streetName = streetName;
		this.streetNum = streetNum;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
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

	public int getAddress_ID() {
		return address_ID;
	}
}
