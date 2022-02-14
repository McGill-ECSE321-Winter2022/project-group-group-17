package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Quantity {

	@Id
	@GeneratedValue
	private int quantity_id;
	
	private int itemNum;
	
	public Quantity(int itemNum) {
		this.itemNum = itemNum;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public int getQuantity_id() {
		return quantity_id;
	}
}
