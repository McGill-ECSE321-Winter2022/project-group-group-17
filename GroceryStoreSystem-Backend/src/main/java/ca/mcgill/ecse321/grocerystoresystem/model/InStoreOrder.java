package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.*;

@Entity
public class InStoreOrder extends Order {
    
    public DeliveryOrder(int totalCost, LocalDateTime orderTimeStamp, boolean isPaid, String orderID) {
        super(totalCost, orderTimeStamp, isPaid, orderID);
    }

}
