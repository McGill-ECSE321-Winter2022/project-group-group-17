package ca.mcgill.ecse321.grocerystoresystem.service;

import ca.mcgill.ecse321.grocerystoresystem.dao.AddressRepository;
import ca.mcgill.ecse321.grocerystoresystem.dao.OwnerRepository;
import ca.mcgill.ecse321.grocerystoresystem.dao.PersonRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.Owner;
import ca.mcgill.ecse321.grocerystoresystem.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public Owner createOwner() {
        Owner owner = new Owner();

        return ownerRepository.save(owner);
    }


    @Transactional
    public Owner createOwner(String first_name, String last_name, String email, String password) {
        if(first_name == null || first_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid First Name");
        if(last_name == null || last_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Last Name");
        if(email == null || email.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Email");
        if(password == null || password.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Password");

        if(personRepository.existsPersonByEmail(email)) throw new IllegalArgumentException("Email already exists in the database");

        Owner owner = new Owner();
        owner.setFirstName(first_name);
        owner.setLastName(last_name);
        owner.setEmail(email);
        owner.setPassword(password);

        return ownerRepository.save(owner);

    }

    @Transactional
    public Owner createOwner(String first_name, String last_name, String email, String password, Address address) {
        if(first_name == null || first_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid First Name");
        if(last_name == null || last_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Last Name");
        if(email == null || email.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Email");
        if(password == null || password.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Password");
        if(address == null) throw new IllegalArgumentException("Please provide valid arguments: Address");

        if(personRepository.existsPersonByEmail(email)) throw new IllegalArgumentException("Email already exists in the database");

        Owner owner = new Owner();
        owner.setFirstName(first_name);
        owner.setLastName(last_name);
        owner.setEmail(email);
        owner.setPassword(password);
        owner.setAddress(address);

        return ownerRepository.save(owner);
    }

    @Transactional
    public boolean deleteOwner(int personID) {
        Owner owner = ownerRepository.findOwnerByPersonID(personID);
        if(owner == null) throw new NullPointerException("Owner not found");

        ownerRepository.delete(owner);

        return !this.isOwnerByID(owner.getPersonID());
    }

    @Transactional
    public boolean deleteOwners() {
        ownerRepository.deleteAll();

        return this.getAllOwners().size() == 0;
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
    public Owner findOwnerByEmail(String email) {
        if(email == null || email.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Email");

        Owner owner = this.ownerRepository.findOwnerByEmail(email);
        if(owner == null) throw new NullPointerException("No owners found");

        return owner;
    }

    @Transactional
    public Owner updateOwnerAddressByID(int personID, int addressID) {
        Owner owner = ownerRepository.findOwnerByPersonID(personID);
        if(owner == null) throw new NullPointerException("Owner not found");

        Address address = addressRepository.findAddressByAddressID(addressID);
        if(address == null) throw new NullPointerException("Address not found");

        owner.setAddress(address);
        return ownerRepository.save(owner);
    }

    @Transactional
    public Owner updateOwnerPasswordById(int personID, String password) {
        if(password == null || password.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid password");

        Owner owner = this.ownerRepository.findOwnerByPersonID(personID);
        if(owner == null) throw new NullPointerException("Owner not found");

        owner.setPassword(password);
        return ownerRepository.save(owner);
    }

    @Transactional
    public boolean logIn(String email, String password) {
        List<Person> persons = personRepository.findPersonByEmail(email);
        if(persons.size() == 0) throw new IllegalArgumentException("No account with that email or password");

        Person person = persons.get(0);
        if(person.getPassword().equals(password)) {
            person.setLoginStatus(true);

            return true;
        }
        else {
            throw new IllegalArgumentException("No account with that email or password");
        }
    }

    @Transactional
    public boolean logOut(Person person) {
        if(!person.getLoginStatus()) {
            throw new IllegalArgumentException("Person not loggoed in");
        }

        person.setLoginStatus(false);
        return true;
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
