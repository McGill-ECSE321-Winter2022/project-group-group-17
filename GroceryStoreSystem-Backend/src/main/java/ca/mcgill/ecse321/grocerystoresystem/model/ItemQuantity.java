package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.*;

@Entity
public class ItemQuantity {

	@Id
	@GeneratedValue
	private int quantity_id;
	
	// ItemQuantity Associations
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderItems")
	private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderItems")
	private Item specificItem;
	
	private int itemNum;
	
	public ItemQuantity(int itemNum) {
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
