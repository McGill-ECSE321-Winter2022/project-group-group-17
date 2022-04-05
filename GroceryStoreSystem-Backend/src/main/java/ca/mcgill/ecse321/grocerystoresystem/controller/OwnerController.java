package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dto.*;
import ca.mcgill.ecse321.grocerystoresystem.model.*;
import ca.mcgill.ecse321.grocerystoresystem.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class OwnerController {

    //TODO: ADD LOGIN/LOGOUT METHODS USING THE EMAIL/PASSWORD


    @Autowired
    private OwnerService ownerService;

    @PostMapping(value = {"/owner/login"})
    public boolean login(@RequestParam String email, @RequestParam String password) {
        try {
            return this.ownerService.logIn(email, password);
        } catch (NullPointerException | IllegalArgumentException exp) {
            System.out.println(exp.getMessage());
            return false;
        }
    }

    @PostMapping(value = {"/owner/logout"})
    public boolean logout(@RequestParam int id) {
        try {
            return this.ownerService.logOut(id);
        } catch (NullPointerException | IllegalArgumentException exp) {
            System.out.println(exp.getMessage());
            return false;
        }
    }

    @GetMapping(value = {"/owners/", "/owners"})
    public List<OwnerDto> getAllOwners() {
        return ownerService.getAllOwners().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping(value = {"/owner/get/id/", "/owner/get/id"})
    public OwnerDto getOwnerWithId(@RequestParam int id) {
        try {
            return convertToDto(ownerService.findOwnerByID(id));
        } catch (NullPointerException exp) {
            return null;
        }
    }

    @GetMapping(value = {"/owner/get/firstname/", "/owner/get/firstname"})
    public List<OwnerDto> getOwnerWithFirstName(@RequestParam String firstname) {
        try {
            return ownerService.findOwnerByFirstName(firstname).stream().map(this::convertToDto).collect(Collectors.toList());
        } catch (NullPointerException exp) {
            return null;
        }
    }

    @GetMapping(value = {"/owner/get/lastname/", "/owner/get/lastname"})
    public List<OwnerDto> getOwnerWithLastName(@RequestParam String lastname) {
        try {
            return ownerService.findOwnerByLastName(lastname).stream().map(this::convertToDto).collect(Collectors.toList());
        } catch (NullPointerException exp) {
            return null;
        }
    }

    @GetMapping(value = {"/owner/get/fullname/", "/owner/get/fullname"})
    public List<OwnerDto> getOwnerWithName(@RequestParam String firstname, @RequestParam String lastname) {
        try {
            return ownerService.findOwnerByName(firstname, lastname).stream().map(this::convertToDto).collect(Collectors.toList());
        } catch (NullPointerException exp) {
            return null;
        }
    }

    @GetMapping(value = {"/owner/get/email/", "/owner/get/email"})
    public OwnerDto getOwnerWithEmail(@RequestParam String email) {
        try {
            return convertToDto(ownerService.findOwnerByEmail(email));
        } catch (NullPointerException exp) {
            return null;
        }
    }

    @PostMapping(value = {"/owner/create/", "/owner/create"})
    public OwnerDto createOwner(@RequestParam String firstname, @RequestParam String lastname,
                                @RequestParam String email, @RequestParam String password) {
        Owner owner = ownerService.createOwner(firstname, lastname, email, password);
        return convertToDto(owner);
    }

    @DeleteMapping(value = {"/owner/delete/", "/owner/delete"})
    public boolean deleteOwnerByID(@RequestParam int id) {
        try {
            return ownerService.deleteOwner(id);
        } catch (NullPointerException exp) {
            return false;
        }
    }

    @DeleteMapping(value = {"/owners/delete/", "/owners/delete"})
    public boolean deleteOwners() {
        ownerService.deleteOwners();

        return true;
    }

    @GetMapping(value = {"/owner/check/id/", "/owner/check/id"})
    public boolean isOwnerWithID(@RequestParam int id) {
        return ownerService.isOwnerByID(id);
    }

    @GetMapping(value = {"/owner/check/firstname/", "/owner/check/firstname"})
    public boolean isOwnerWithFirstName(@RequestParam String firstname) {
        return ownerService.isOwnerByFirstName(firstname);
    }

    @GetMapping(value = {"/owner/check/lastname/", "/owner/check/lastname"})
    public boolean isOwnerWithLastName(@RequestParam String lastname) {
        return ownerService.isOwnerByLastName(lastname);
    }

    @GetMapping(value = {"/owner/check/email/", "/owner/check/email"})
    public boolean isOwnerWithEmail(@RequestParam String email) {
        return ownerService.isOwnerByEmail(email);
    }

    @GetMapping(value = {"/owner/check/fullname/", "/owner/check/fullname"})
    public boolean isOwnerWithFirstNameAndLastName(@RequestParam String firstname, @RequestParam String lastname) {
        return ownerService.isOwnerByFirstAndLastName(firstname, lastname);
    }

    @PostMapping(value = {"/owner/update/address/", "/owner/update/address"})
    public OwnerDto updateOwnerAddress(@RequestParam int id, @RequestParam int addressID) {
        try {
            return convertToDto(ownerService.updateOwnerAddressByID(id, addressID));
        } catch (NullPointerException exp) {
            return null;
        }
    }

    @PostMapping(value = {"/owner/update/password", "/owner/update/password/"})
    public OwnerDto updateOwnerPassword(@RequestParam int id, @RequestParam String password) {
        try {
            return convertToDto(ownerService.updateOwnerPasswordById(id, password));
        } catch (NullPointerException exp) {
            return null;
        }
    }

    private OwnerDto convertToDto(Owner owner) throws NullPointerException {
        if (owner == null) {
            throw new NullPointerException("Owner is null");
        }

        if (owner.getAddress() != null && owner.getOrders() != null) {
            return new OwnerDto(owner.getPersonID(), owner.getFirstName(), owner.getLastName(), owner.getEmail(), convertToDto(owner.getAddress()), convertToOrderDto(owner.getOrders()));
        } else if (owner.getAddress() == null && owner.getOrders() != null) {
            return new OwnerDto(owner.getPersonID(), owner.getFirstName(), owner.getLastName(), owner.getEmail(), convertToDto(new Address()), convertToOrderDto(owner.getOrders()));
        } else if (owner.getAddress() != null && owner.getOrders() == null) {
            return new OwnerDto(owner.getPersonID(), owner.getFirstName(), owner.getLastName(), owner.getEmail(), convertToDto(new Address()), new ArrayList<>());
        } else {
            return new OwnerDto(owner.getPersonID(), owner.getFirstName(), owner.getLastName(), owner.getEmail(), convertToDto(new Address()), new ArrayList<>());
        }

    }

    private AddressDto convertToDto(Address address) {
        if (address == null) {
            throw new NullPointerException("Cannot find Address");
        }
        return new AddressDto(address.getAddressID(), address.isLocal(), address.getStreetName(), address.getCity(), address.getPostalCode(), address.getStreetNum(), address.getCountry());
    }

    private List<OrderDto> convertToOrderDto(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();

        for (Order order : orders) {
            OrderDto orderDto = convertToDto(order);
            orderDtos.add(orderDto);
        }

        return orderDtos;
    }

    private OrderDto convertToDto(Order order) {
        if (order == null) {
            throw new NullPointerException("Order is null");
        }

        if (order instanceof DeliveryOrder) {
            return convertToDto((DeliveryOrder) order);
        } else if (order instanceof PickupOrder) {
            return convertToDto((PickupOrder) order);
        } else {
            return convertToDto((InStoreOrder) order);
        }
    }

    private OrderDto convertToDto(DeliveryOrder deliveryOrder) {
        if (deliveryOrder == null) {
            throw new NullPointerException("Order is null");
        }

        if (deliveryOrder.getPortionNum() != null && deliveryOrder.getAddress() != null) {
            return new OrderDto(deliveryOrder.getOrderID(), deliveryOrder.getTotalCost(),
                    deliveryOrder.getOrderTimeStamp(), deliveryOrder.isPaid(),
                    deliveryOrder.getDeliveryTime(), convertToDto(deliveryOrder.getAddress()),
                    convertToDto(deliveryOrder.getPortionNum()));
        } else if (deliveryOrder.getPortionNum() != null && deliveryOrder.getAddress() == null) {
            return new OrderDto(deliveryOrder.getOrderID(), deliveryOrder.getTotalCost(),
                    deliveryOrder.getOrderTimeStamp(), deliveryOrder.isPaid(),
                    deliveryOrder.getDeliveryTime(), convertToDto(new Address()),
                    convertToDto(deliveryOrder.getPortionNum()));
        } else if (deliveryOrder.getPortionNum() == null && deliveryOrder.getAddress() != null) {
            return new OrderDto(deliveryOrder.getOrderID(), deliveryOrder.getTotalCost(),
                    deliveryOrder.getOrderTimeStamp(), deliveryOrder.isPaid(),
                    deliveryOrder.getDeliveryTime(), convertToDto(deliveryOrder.getAddress()),
                    new ArrayList<>());
        } else {
            return new OrderDto(deliveryOrder.getOrderID(), deliveryOrder.getTotalCost(),
                    deliveryOrder.getOrderTimeStamp(), deliveryOrder.isPaid(),
                    deliveryOrder.getDeliveryTime(), convertToDto(new Address()),
                    new ArrayList<>());

        }
    }

    private OrderDto convertToDto(PickupOrder pickupOrder) {
        if (pickupOrder == null) {
            throw new NullPointerException("Order is null");
        }

        if (pickupOrder.getPortionNum() != null)
            return new OrderDto(pickupOrder.getOrderID(), pickupOrder.getTotalCost(), pickupOrder.getOrderTimeStamp(),
                    pickupOrder.isPaid(), pickupOrder.getPickupDate(), convertToDto(pickupOrder.getPortionNum()));

        else {

            return new OrderDto(pickupOrder.getOrderID(), pickupOrder.getTotalCost(), pickupOrder.getOrderTimeStamp(),
                    pickupOrder.isPaid(), pickupOrder.getPickupDate(), new ArrayList<>());
        }

    }

    private List<ItemQuantityDto> convertToDto(List<ItemQuantity> itemQuantities) {
        List<ItemQuantityDto> itemQuantityDtos = new ArrayList<>();

        for (ItemQuantity itemQuantity : itemQuantities) {
            ItemQuantityDto itemQuantityDto = convertToDto(itemQuantity);
            itemQuantityDtos.add(itemQuantityDto);
        }

        return itemQuantityDtos;
    }

    private OrderDto convertToDto(InStoreOrder inStoreOrder) {
        if (inStoreOrder == null) {
            throw new NullPointerException("Order is null");
        }

        if (inStoreOrder.getPortionNum() != null)
            return new OrderDto(inStoreOrder.getOrderID(), inStoreOrder.getTotalCost(), inStoreOrder.getOrderTimeStamp(),
                    inStoreOrder.isPaid(), convertToDto(inStoreOrder.getPortionNum()));

        else {

            return new OrderDto(inStoreOrder.getOrderID(), inStoreOrder.getTotalCost(), inStoreOrder.getOrderTimeStamp(),
                    inStoreOrder.isPaid(), new ArrayList<>());
        }
    }

    private ItemQuantityDto convertToDto(ItemQuantity itemQuantity) {
        if (itemQuantity == null) {
            throw new NullPointerException("Item Quantity is null");
        }
        return new ItemQuantityDto(itemQuantity.getItemNum(), itemQuantity.getQuantityID());
    }
}
