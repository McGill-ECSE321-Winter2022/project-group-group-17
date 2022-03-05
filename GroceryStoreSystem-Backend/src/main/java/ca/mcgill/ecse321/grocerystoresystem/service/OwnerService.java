package ca.mcgill.ecse321.grocerystoresystem.service;

import ca.mcgill.ecse321.grocerystoresystem.dao.OwnerRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Transactional
    public Owner createOwner() {
        Owner owner = new Owner();

        ownerRepository.save(owner);
        return owner;
    }


    @Transactional
    public Owner createOwner(String first_name, String last_name, String email, String password) {
        if(first_name == null || first_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid First Name");
        if(last_name == null || last_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Last Name");
        if(email == null || email.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Email");
        if(password == null || password.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Password");

        Owner owner = new Owner();
        owner.setFirstName(first_name);
        owner.setLastName(last_name);
        owner.setEmail(email);
        owner.setEmail(password);

        ownerRepository.save(owner);

        return owner;
    }

    @Transactional
    public Owner createOwner(String first_name, String last_name, String email, String password, Address address) {
        if(first_name == null || first_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid First Name");
        if(last_name == null || last_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Last Name");
        if(email == null || email.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Email");
        if(password == null || password.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Password");
        if(address == null) throw new IllegalArgumentException("Please provide valid arguments: Address");

        Owner owner = new Owner();
        owner.setFirstName(first_name);
        owner.setLastName(last_name);
        owner.setEmail(email);
        owner.setPassword(password);
        owner.setAddress(address);

        ownerRepository.save(owner);

        return owner;
    }

    @Transactional
    public boolean deleteOwner(int personID) {
        Owner owner = ownerRepository.findOwnerByPersonID(personID);
        if(owner == null) throw new NullPointerException("Owner not found");
        ownerRepository.delete(owner);

        return true;
    }

    @Transactional
    public boolean isOwnerByID(int personID) {
        return ownerRepository.existsByPersonID(personID);
    }

    @Transactional
    public boolean isOwnerByFirstName(String first_name) {
        if(first_name == null || first_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid First Name");

        return ownerRepository.existsByFirstName(first_name);
    }

    @Transactional
    public boolean isOwnerByLastName(String last_name) {
        if(last_name == null || last_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Last Name");

        return ownerRepository.existsByLastName(last_name);
    }

    @Transactional
    public boolean isOwnerByFirstAndLastName(String first_name, String last_name) {
        if(first_name == null || first_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid First Name");
        if(last_name == null || last_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Last Name");

        return ownerRepository.existsByFirstNameAndLastName(first_name, last_name);
    }

    @Transactional
    public boolean isOwnerByEmail(String email) {
        if(email == null || email.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Email");

        return ownerRepository.existsByEmail(email);
    }

    @Transactional
    public Owner findOwnerByID(int personID) {
        Owner owner = ownerRepository.findOwnerByPersonID(personID);
        if(owner == null) throw new NullPointerException("Owner not found");

        return owner;
    }

    @Transactional
    public List<Owner> findOwnerByFirstName(String first_name) {
        if(first_name == null || first_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid First Name");
        List<Owner> owners = this.ownerRepository.findOwnerByFirstName(first_name);

        if(owners.size() == 0) throw new NullPointerException("No owners found");
        return owners;
    }

    @Transactional
    public List<Owner> findOwnerByLastName(String last_name) {
        if(last_name == null || last_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Last Name");
        List<Owner> owners = this.ownerRepository.findOwnerByLastName(last_name);

        if(owners.size() == 0) throw new NullPointerException("No owners found");
        return owners;
    }

    @Transactional
    public List<Owner> findOwnerByName(String first_name, String last_name) {
        if(first_name == null || first_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid First Name");
        if(last_name == null || last_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Last Name");
        List<Owner> owners = this.ownerRepository.findOwnerByFirstNameAndLastName(first_name, last_name);

        if(owners.size() == 0) throw new NullPointerException("No owners found");
        return owners;
    }

    @Transactional
    public List<Owner> findOwnerByEmail(String email) {
        if(email == null || email.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Email");

        List<Owner> owners = this.ownerRepository.findOwnerByEmail("No owners found");
        if(owners.size() == 0) throw new NullPointerException("No owners found");

        return owners;
    }

    @Transactional
    public List<Owner> getAllOwners() {
        return toList(ownerRepository.findAll());
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
