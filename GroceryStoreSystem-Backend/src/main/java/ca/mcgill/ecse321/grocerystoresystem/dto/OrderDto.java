package ca.mcgill.ecse321.grocerystoresystem.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private int id;
    private int totalCost;
    private LocalDateTime orderTimeStamp;
    private boolean isPaid;
    private LocalDateTime deliveryTime;
    private LocalDateTime pickupDate;
    private AddressDto deliveryAddress;
    private List<ItemQuantityDto> itemQuantities;
    private String orderType;

    public OrderDto(){}

    public OrderDto(int id, int totalCost, LocalDateTime orderTimeStamp, boolean isPaid,
                    LocalDateTime deliveryTime, AddressDto deliveryAddress,
                    List<ItemQuantityDto> itemQuantities) {
        this.id = id;
        this.totalCost = totalCost;
        this.orderTimeStamp = orderTimeStamp;
        this.isPaid = isPaid;
        this.deliveryTime = deliveryTime;
        this.deliveryAddress = deliveryAddress;
        this.itemQuantities = itemQuantities;
        this.orderType = "DeliveryOrder";
    }

    public OrderDto(int id, int totalCost, LocalDateTime orderTimeStamp, boolean isPaid,
                    List<ItemQuantityDto> itemQuantities) {
        this.id = id;
        this.totalCost = totalCost;
        this.orderTimeStamp = orderTimeStamp;
        this.isPaid = isPaid;
        this.itemQuantities = itemQuantities;
        this.orderType = "InStoreOrder";
    }

    public OrderDto( int id, int totalCost, LocalDateTime orderTimestamp,
                     boolean isPaid, LocalDateTime pickupDate,
                     List<ItemQuantityDto> itemQuantities) {
        this.id = id;
        this.totalCost = totalCost;
        this.orderTimeStamp = orderTimestamp;
        this.isPaid = isPaid;
        this.pickupDate = pickupDate;
        this.orderType = "PickupOrder";
        this.itemQuantities = itemQuantities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public void setOrderTimeStamp(LocalDateTime orderTimeStamp) {
        this.orderTimeStamp = orderTimeStamp;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setDeliveryAddress(AddressDto deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public LocalDateTime getOrderTimeStamp() {
        return orderTimeStamp;
    }

    public boolean getPaymentStatus() {
        return isPaid;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public AddressDto getDeliveryAddress() {
        return deliveryAddress;
    }

    public List<ItemQuantityDto> getItemQuantities() { return this.itemQuantities; }

    public void setItemQuantities(ArrayList<ItemQuantityDto> itemQuantities) {
        this.itemQuantities = itemQuantities;
    }

    public LocalDateTime getPickupDate() {
        return pickupDate;
    }

    public String getOrderType() {
        return orderType;
    }

}
