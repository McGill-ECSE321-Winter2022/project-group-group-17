package ca.mcgill.ecse321.grocerystoresystem.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class InStoreOrder extends Order {
    
	public InStoreOrder() {};
	
	
	public InStoreOrder(int totalCost, LocalDateTime orderTimeStamp, boolean isPaid) {
        super(totalCost, orderTimeStamp, isPaid);
    }
	
    public InStoreOrder(int totalCost, LocalDateTime orderTimeStamp, boolean isPaid, int orderID) {
        super(totalCost, orderTimeStamp, isPaid, orderID);
    }

}
