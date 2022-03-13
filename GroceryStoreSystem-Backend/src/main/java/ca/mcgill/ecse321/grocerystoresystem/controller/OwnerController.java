package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dto.AddressDto;
import ca.mcgill.ecse321.grocerystoresystem.dto.OwnerDto;
import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.Owner;
import ca.mcgill.ecse321.grocerystoresystem.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class OwnerController {

    //TODO: ADD LOGIN/LOGOUT METHODS USING THE EMAIL/PASSWORD


    @Autowired
    private OwnerService ownerService;

    @GetMapping(value = {"/owners/", "/owners"})
    public List<OwnerDto> getAllOwners() {
        return ownerService.getAllOwners().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping(value={"/owner/get/id/", "/owner/get/id"})
    public OwnerDto getOwnerWithId(@RequestParam int id) {
        try {
            return convertToDto(ownerService.findOwnerByID(id));
        }
        catch (NullPointerException exp) {
            return null;
        }
    }

    @GetMapping(value = {"/owner/get/firstname/", "/owner/get/firstname"})
    public List<OwnerDto> getOwnerWithFirstName(@RequestParam String firstname) {
        try {
            return ownerService.findOwnerByFirstName(firstname).stream().map(this::convertToDto).collect(Collectors.toList());
        }
        catch (NullPointerException exp) {
            return null;
        }
    }

    @GetMapping(value = {"/owner/get/lastname/", "/owner/get/lastname"})
    public List<OwnerDto> getOwnerWithLastName(@RequestParam String lastname) {
        try {
            return ownerService.findOwnerByLastName(lastname).stream().map(this::convertToDto).collect(Collectors.toList());
        }
        catch (NullPointerException exp) {
            return null;
        }
    }

    @GetMapping(value = {"/owner/get/fullname/", "/owner/get/fullname"})
    public List<OwnerDto> getOwnerWithName(@RequestParam String firstname, @RequestParam String lastname) {
        try {
            return ownerService.findOwnerByName(firstname, lastname).stream().map(this::convertToDto).collect(Collectors.toList());
        }
        catch (NullPointerException exp) {
            return null;
        }
    }

    @GetMapping(value = {"/owner/get/email/", "/owner/get/email"})
    public OwnerDto getOwnerWithEmail(@RequestParam String email) {
        try {
            return convertToDto(ownerService.findOwnerByEmail(email));
        }
        catch (NullPointerException exp) {
            return null;
        }
    }

    @PostMapping(value = {"/owner/create/", "/owner/create"})
    public OwnerDto createOwner(@RequestParam String firstname, @RequestParam String lastname,
                                @RequestParam String email, @RequestParam String password) {
        Owner owner = ownerService.createOwner(firstname, lastname, email, password);
        return convertToDto(owner);
    }

    @DeleteMapping(value={"/owner/delete/", "/owner/delete"})
    public boolean deleteOwnerByID(@RequestParam int id) {
        try {
            return ownerService.deleteOwner(id);
        }
        catch (NullPointerException exp) {
            return false;
        }
    }

    @DeleteMapping(value={"/owners/delete/", "/owners/delete"})
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

    @PostMapping(value={"/owner/update/address/", "/owner/update/address"})
    public OwnerDto updateOwnerAddress(@RequestParam int id, @RequestParam int addressID) {
        try {
            return convertToDto(ownerService.updateOwnerAddressByID(id, addressID));
        }
        catch(NullPointerException exp) {
            return null;
        }
    }

    @PostMapping(value = {"/owner/update/password", "/owner/update/password/"})
    public OwnerDto updateOwnerPassword(@RequestParam int id, @RequestParam String password) {
        try {
            return convertToDto(ownerService.updateOwnerPasswordById(id, password));
        }
        catch(NullPointerException exp) {
            return null;
        }
    }

    private OwnerDto convertToDto(Owner o) throws NullPointerException{
        if(o == null) {
            throw new NullPointerException("Owner is null");
        }

        if(o.getAddress() != null) {
            return new OwnerDto(o.getPersonID(), o.getFirstName(), o.getLastName(), o.getEmail(),convertToDto(o.getAddress()));
        }

        return new OwnerDto(o.getPersonID(), o.getFirstName(), o.getLastName(), o.getEmail());
    }

    private AddressDto convertToDto(Address a) throws NullPointerException {
        if(a == null) {
            throw new NullPointerException("Address is null");
        }

        return new AddressDto(a.getAddressID(), a.isLocal(), a.getStreetName(), a.getStreetNum(), a.getCity(), a.getPostalCode(), a.getCountry());
    }
}
