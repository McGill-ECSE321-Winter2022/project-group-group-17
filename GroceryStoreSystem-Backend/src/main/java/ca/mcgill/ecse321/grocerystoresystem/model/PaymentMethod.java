package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PaymentMethod {
	
	private String name;
	private String cardNum;
	private Date  expiryDate;
	private String cVC;
	
	@OneToOne(mappedBy = "payment")
	private Address address;
	@ManyToOne(mappedBy = "methods",fetch = FetchType.LAZY)
	private Person person;
	
	public PaymentMethod(String name, String cardNum, Date expiryDate, String cVC) {
		this.name = name;
		this.cardNum = cardNum;
		this.expiryDate = expiryDate;
		this.cVC = cVC;
	}
	
	public  String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public  String getCardNum() {
		return cardNum;
	}
	
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	
	public  String getCVC() {
		return cVC;
	}
	
	public void setCVC(String cVC) {
		this.cVC = cVC;
	}
	public  Date getExpiryDate() {
		return expiryDate;
	}
	
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getPaymentMethod_ID() {
		return paymentMethod_ID;
	}
	
}
