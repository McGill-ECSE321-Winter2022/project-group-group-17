package ca.mcgill.ecse321.grocerystoresystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.grocerystoresystem.service.AddressService;
import ca.mcgill.ecse321.grocerystoresystem.dto.AddressDto;
import ca.mcgill.ecse321.grocerystoresystem.model.Address;

@CrossOrigin(origins = "*")
@RestController
public class AddressController {
	@Autowired
	private AddressService addressService;
	
    @GetMapping(value={"/address/get/id/", "/address/get/id"})
    public AddressDto getAddressWithId(@RequestParam int id) {
        try {
            return convertToDto(addressService.getAddressWithID(id));
        }
        catch (NullPointerException e) {
            return null;
        }
    }
	
	@GetMapping(value = { "/addresses", "/addresses/" })
	public List<AddressDto> getAllAddresses() {
		return addressService.getAllAddresses().stream().map(this::convertToDto).collect(Collectors.toList());
	}
	@GetMapping(value = {"/address/get/streetname", "/address/get/streetname/"})
	public List<AddressDto> getAddressWithStreetName(@RequestParam String streetName){
		try {
			return addressService.getAddressWithStreetName(streetName).stream().map(this::convertToDto).collect(Collectors.toList());
		}catch(NullPointerException e) {
			return null;
		}
	}

	@GetMapping(value = {"/adress/get/streetnum", "/address/get/streetnum/"})
	public List<AddressDto> getAddressWithStreetNum(@RequestParam String streetNum){
		try {
			return addressService.getAddressWithStreetNum(streetNum).stream().map(this::convertToDto).collect(Collectors.toList());
		}catch(NullPointerException e) {
			return null;
		}
	}

	@GetMapping(value = {"/address/get/postalcode", "/address/get/postalcode/"})
	public List<AddressDto> getAddressWithPostalCode(@RequestParam String postalCode){
		try {
			return addressService.getAddressWithPostalCode(postalCode).stream().map(this::convertToDto).collect(Collectors.toList());
		}catch(NullPointerException e) {
			return null;
		}
	}

	@GetMapping(value = {"/address/get/country", "/address/get/country/"})
	public List<AddressDto> getAddressCountry(@RequestParam String country){
		try {
			return addressService.getAddressWithPostalCode((country)).stream().map(this::convertToDto).collect(Collectors.toList());
		}catch(NullPointerException e) {
			return null;
		}
	}

	@GetMapping(value = {"/address/get/islocal", "/address/get/islocal/"})
	public List<AddressDto> getAddressWithIsLocal(@RequestParam boolean isLocal){
		try {
			return addressService.getAddressWithIsLocal(isLocal).stream().map(this::convertToDto).collect(Collectors.toList());
		}catch(NullPointerException e) {
			return null;
		}
	}

	@GetMapping(value = {"/address/get/city", "/address/get/city/"})
	public List<AddressDto> getAddressWithCity(@RequestParam String city){
		try {
			return addressService.getAddressWithCity(city).stream().map(this::convertToDto).collect(Collectors.toList());
		}catch(NullPointerException e) {
			return null;
		}
	}

	@GetMapping(value = {"/address/get/streetnamenum", "/address/get/streetnamenum/"})
	public List<AddressDto> getAddressWithStreetNumAndName(@RequestParam String streetNum, @RequestParam String streetName){
		try {
			return addressService.getAddressWithStreetNumAndName(streetNum, streetName).stream().map(this::convertToDto).collect(Collectors.toList());
		}catch(NullPointerException e) {
			return null;
		}
	}

	@PostMapping(value = { "/address/create", "/address/create/" })
	public AddressDto createAddress(@RequestParam String streetName, @RequestParam String streetNum,
									@RequestParam String city,  @RequestParam String postalCode,
									@RequestParam String country,  @RequestParam boolean isLocal)
		throws IllegalArgumentException {
		Address address = addressService.createAddress(streetName, streetNum, city, postalCode, country, isLocal);
		return convertToDto(address);
	}

	@DeleteMapping (value= {"/address/delete","/address/delete/"})
	public boolean deleteAddressByID(@RequestParam int id) {
		try {
			return addressService.deleteAddress(id);
			
		}catch(NullPointerException e) {
			return false;
		}
	}
	private AddressDto convertToDto(Address e) {
		if (e == null) {
			throw new IllegalArgumentException("There is no such Address");
		}

		return  new AddressDto(e.getAddressID(),e.isLocal(),e.getStreetName(),e.getStreetNum(),e.getCity(),e.getPostalCode(), e.getCountry());
	}

	

}
