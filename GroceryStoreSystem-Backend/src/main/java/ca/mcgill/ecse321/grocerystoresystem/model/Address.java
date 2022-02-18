package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.*;


@Entity
public class Address {
	
	@Id
	@GeneratedValue
	private String addressID;
	
	// Address Associations
	@OneToMany(mappedBy = "address")
	private Person person;
	@OneToMany(mappedBy = "address")
	private DeliveryOrder order;
	
	private boolean isLocal;
	
	private String streetName;
	private String streetNum;
	
	private String city;
	private String postalCode;
	
	private String country;
	
	public Address(String addressID, String streetName, String streetNum, String city, String postalCode, String country, boolean isLocal) {
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

	public String getAddressID() {
		return addressID;
	}
	
//	public void setAddressID(String addressID) {
//	  this.addressID = addressID;
//	}
	
}
