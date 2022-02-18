package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.*;

@Entity
public class ItemQuantity {

	@Id
	@GeneratedValue
	private String quantityID;
	
	// ItemQuantity Associations
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderItems")
	private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderItems")
	private Item specificItem;
	
	private int itemNum;
	
	public ItemQuantity(int itemNum, String quantityID ) {
		this.itemNum = itemNum;
		this.quantityID = quantityID;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public String getQuantityID() {
		return quantityID;
	}
	public void setQuantityID(String quantityID){
		this.quantityID = quantityID;
	}
}
