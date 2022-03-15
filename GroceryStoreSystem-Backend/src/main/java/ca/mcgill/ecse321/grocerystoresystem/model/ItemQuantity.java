package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.*;

@Entity
public class ItemQuantity {

	@Id
	@GeneratedValue
	private int quantityID;
	
	// ItemQuantity Associations
	@ManyToOne
	private Order order;

	@ManyToOne
	private Item specificItem;
	
	private int itemNum;
	
	public ItemQuantity() {};
	
	public ItemQuantity(int itemNum) {
		this.itemNum = itemNum;
	}
	
	public ItemQuantity(int itemNum, int quantityID ) {
		this.itemNum = itemNum;
		this.quantityID = quantityID;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public int getQuantityID() {
		return quantityID;
	}
	public void setQuantityID(int quantityID){
		this.quantityID = quantityID;
	}
}
