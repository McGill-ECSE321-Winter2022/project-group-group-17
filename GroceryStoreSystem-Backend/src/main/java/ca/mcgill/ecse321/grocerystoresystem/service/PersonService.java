package ca.mcgill.ecse321.grocerystoresystem.service;

import ca.mcgill.ecse321.grocerystoresystem.dao.OrderRepository;
import ca.mcgill.ecse321.grocerystoresystem.dao.PersonRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.Order;
import ca.mcgill.ecse321.grocerystoresystem.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public List<Order> getOrdersWithPersonID(int personID){
        Person person = personRepository.findPersonByPersonID(personID);
        if (person == null) throw new NullPointerException("No person with such ID was found");
        return toList(orderRepository.findOrderByPersonPersonID(person.getPersonID()));
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
