package ca.mcgill.ecse321.grocerystoresystem.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class PaymentMethod {
	@Id
	@GeneratedValue
	private int paymentMethod_ID;
	private String name;
	private String cardNum;
	private LocalDateTime  expiryDate;
	private String cVC;
	
	@OneToOne(mappedBy = "payment")
	private Address address;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="methods")
	private Person person;
	
	public PaymentMethod(String name, String cardNum, LocalDateTime expiryDate, String cVC) {
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
	public  LocalDateTime getExpiryDate() {
		return expiryDate;
	}
	
	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getPaymentMethod_ID() {
		return paymentMethod_ID;
	}
	
}
