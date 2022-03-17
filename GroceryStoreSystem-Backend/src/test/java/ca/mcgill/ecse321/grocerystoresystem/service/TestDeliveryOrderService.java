package ca.mcgill.ecse321.grocerystoresystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.DeliveryOrder;
import ca.mcgill.ecse321.grocerystoresystem.dao.AddressRepository;
import ca.mcgill.ecse321.grocerystoresystem.dao.DeliveryOrderRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestDeliveryOrderService {
	
	@Mock 
	private DeliveryOrderRepository deliveryOrderRepository;
	
	@InjectMocks
	private DeliveryOrderService deliveryOrderService;
	
	@Mock
	private AddressRepository addressRepository;
	
	@InjectMocks
	private AddressService addressService;
	
	private static final int ORDER_ID = 1234;
	private static final int TOTAL_COST = 1000;
	private static final LocalDateTime ORDER_TIME_STAMP = LocalDateTime.of(2022, 01,01,01,01,01,01);
	private static final boolean IS_PAID = true;
	private static final LocalDateTime DELIVERY_TIME = LocalDateTime.of(2022,01,02,01,01,01,01);


	private static final int ORDER_ID2 = 1000;
	private static final int TOTAL_COST2 = 1234;
	private static final LocalDateTime ORDER_TIME_STAMP2 = LocalDateTime.of(2022, 02,01,01,01,01,01);
	private static final boolean IS_PAID2 = true;
	private static final LocalDateTime DELIVERY_TIME2 = LocalDateTime.of(2022,02,02,01,01,01,01);

	private static final int ORDER_ID3 = 1111;
	private static final int TOTAL_COST3 = 3404;
	private static final LocalDateTime ORDER_TIME_STAMP3 = LocalDateTime.of(2022, 03,01,01,01,01,01);
	private static final boolean IS_PAID3 = true;
	private static final LocalDateTime DELIVERY_TIME3 = LocalDateTime.of(2022,03,02,01,01,01,01);

	private static final int ADDRESS_KEY = 1001;
    private static final String STREET_NUM = "1239";
    private static final String STREET_NAME = "Peel";
    private static final String CITY = "Montreal";
    private static final String POSTAL_CODE = "HHH HHH";
    private static final String COUNTRY = "Canada";
    private static final boolean IS_LOCAL = true;


	private static final int PERSON_KEY = 1002;

	
	@BeforeEach
	public void setMockOutput() {
		
		 lenient().when(deliveryOrderRepository.findDeliveryOrderByOrderID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	            if(invocation.getArgument(0).equals(ORDER_ID)) {
	                return createDeliveryOrder();
	            } else {
	                return null;
	            }
	        });

		 lenient().when(deliveryOrderRepository.findDeliveryOrderByDeliveryAddressAddressID(anyInt()))
				 .thenAnswer((InvocationOnMock invocation) -> {
					 if(invocation.getArgument(0).equals(ADDRESS_KEY)) {
					 	List<DeliveryOrder> orders = createDeliveryOrders();
					 	return orders;
					 } else {
					 	return new ArrayList<>();
					 }

				 });

		lenient().when(deliveryOrderRepository.findDeliveryOrderByPersonPersonID(anyInt()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if(invocation.getArgument(0).equals(PERSON_KEY)) {
						List<DeliveryOrder> orders = createDeliveryOrders2();
						return orders;
					} else {
						return new ArrayList<>();
					}

				});

		 lenient().when(addressRepository.findAddressByAddressID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
	            if(invocation.getArgument(0).equals(ADDRESS_KEY)) {
	                return createAddress();
	            }
	            else {
	                return null;
	            }
	        });

		lenient().when(deliveryOrderRepository.existsByOrderID(ORDER_ID)).thenAnswer((InvocationOnMock invocation) -> 
		invocation.getArgument(0).equals(ORDER_ID));
		
		
		 Answer<?> returnParamAsAnswer = (InvocationOnMock invocation) -> {return invocation.getArgument(0);};
		 lenient().when(deliveryOrderRepository.save(any(DeliveryOrder.class))).thenAnswer(returnParamAsAnswer);
	}
	
	 @Test
	 public void successfullyCreateDeliveryOrderWithoutAddress() {
		 DeliveryOrder savedOrder = null;
		 
		 try {
			savedOrder = deliveryOrderService.createDeliveryOrder(TOTAL_COST, 
	        		ORDER_TIME_STAMP, IS_PAID, DELIVERY_TIME);
	        
		 } catch(IllegalArgumentException exp) {
			 fail(exp.getMessage());
		 }
	        
	     assertNotNull(savedOrder);
	     assertEquals(TOTAL_COST, savedOrder.getTotalCost());
	     assertEquals(ORDER_TIME_STAMP, savedOrder.getOrderTimeStamp());
	     assertEquals(IS_PAID, savedOrder.isPaid());
	     assertEquals(DELIVERY_TIME, savedOrder.getDeliveryTime());   
	    }
	 
	 @Test
	 public void successfullycreateDeliveryOrderWithAddress() {
		 DeliveryOrder savedOrder = null;
		 
		 try {
			 savedOrder = deliveryOrderService.createDeliveryOrder(TOTAL_COST, ORDER_TIME_STAMP, 
				 IS_PAID, DELIVERY_TIME, createAddress());
		 } catch(IllegalArgumentException exp) {
			 fail(exp.getMessage());
		 }
		 
		 assertNotNull(savedOrder);
	     assertEquals(TOTAL_COST, savedOrder.getTotalCost());
	     assertEquals(ORDER_TIME_STAMP, savedOrder.getOrderTimeStamp());
	     assertEquals(IS_PAID, savedOrder.isPaid());
	     assertEquals(DELIVERY_TIME, savedOrder.getDeliveryTime());
		 
	     assertNotNull(savedOrder.getAddress());
	     assertEquals(STREET_NAME, savedOrder.getAddress().getStreetName() );
	     assertEquals(STREET_NUM, savedOrder.getAddress().getStreetNum());
	     assertEquals(CITY, savedOrder.getAddress().getCity());
	     assertEquals(COUNTRY, savedOrder.getAddress().getCountry());
	     assertEquals(POSTAL_CODE,savedOrder.getAddress().getPostalCode());
	     assertEquals(IS_LOCAL, savedOrder.getAddress().isLocal());
	     assertEquals(ADDRESS_KEY, savedOrder.getAddress().getAddressID());
	 }
	 
	 @Test
	 public void unsuccessfullyCreateDeliveryOrderWithNegativeTotalCost() {
		 DeliveryOrder savedOrder = null;
		 String error = "";
		 
		 try {
			 savedOrder = deliveryOrderService.createDeliveryOrder(-1000, ORDER_TIME_STAMP, 
					 IS_PAID, DELIVERY_TIME);
		 } catch (IllegalArgumentException exception) {
			 error = exception.getMessage();
		 }
		 
		 assertNull(savedOrder);
		 assertEquals("Please submit a valid totalCost", error);
	 }

	 @Test
	 public void successfullyGetDeliveryOrdersWithPerson(){
		 List<DeliveryOrder> savedOrder = null;

		 savedOrder = deliveryOrderService.getAllDeliveryOrdersOfPersonWithPersonID(PERSON_KEY);

		 assertNotNull(savedOrder);
		 assertEquals(1, savedOrder.size());
	 }

	 @Test
	 public void successfullyGetDeliveryOrdersWithAddress(){
		List<DeliveryOrder> savedOrder = null;

		savedOrder = deliveryOrderService.getDeliveryOrdersForAddressWithAddressID(ADDRESS_KEY);

		assertNotNull(savedOrder);
		assertEquals(2, savedOrder.size());
	 }

	@Test
	public void successfullyGetDeliveryOrderByID() {
		DeliveryOrder deliveryOrder = null;

		try {
			deliveryOrder = deliveryOrderService.getDeliveryOrderByID(ORDER_ID);
		} catch(NullPointerException exception) {
			fail(exception.getMessage());
		}

		assertNotNull(deliveryOrder);
		assertEquals(TOTAL_COST, deliveryOrder.getTotalCost());
		assertEquals(ORDER_TIME_STAMP, deliveryOrder.getOrderTimeStamp());
		assertEquals(IS_PAID, deliveryOrder.isPaid());
		assertEquals(DELIVERY_TIME, deliveryOrder.getDeliveryTime());
	}
	
	private DeliveryOrder createDeliveryOrder() {
		DeliveryOrder deliveryOrder = new DeliveryOrder();
		deliveryOrder.setOrderID(ORDER_ID);
		deliveryOrder.setTotalCost(TOTAL_COST);
		deliveryOrder.setOrderTimeStamp(ORDER_TIME_STAMP);
		deliveryOrder.setPaid(IS_PAID);
		deliveryOrder.setDeliveryTime(DELIVERY_TIME);
		return deliveryOrder;
	}
		
		
	
	private Address createAddress() {
		Address address = new Address();
		address.setAddressID(ADDRESS_KEY);
		address.setCity(CITY);
		address.setPostalCode(POSTAL_CODE);
		address.setCountry(COUNTRY);
		address.setStreetName(STREET_NAME);
		address.setStreetNum(STREET_NUM);
		address.setLocal(IS_LOCAL);
		return address;
		
	}

	private List<DeliveryOrder> createDeliveryOrders(){
		ArrayList<DeliveryOrder> orders = new ArrayList<>();

		DeliveryOrder order = new DeliveryOrder();
		order.setOrderID(ORDER_ID);
		order.setTotalCost(TOTAL_COST);
		order.setOrderTimeStamp(ORDER_TIME_STAMP);
		order.setPaid(IS_PAID);
		order.setDeliveryTime(DELIVERY_TIME);

		DeliveryOrder order2 = new DeliveryOrder();
		order2.setOrderID(ORDER_ID2);
		order2.setTotalCost(TOTAL_COST2);
		order2.setOrderTimeStamp(ORDER_TIME_STAMP2);
		order2.setPaid(IS_PAID2);
		order2.setDeliveryTime(DELIVERY_TIME2);

		orders.add(order);
		orders.add(order2);

		return orders;
	}


	private List<DeliveryOrder> createDeliveryOrders2(){
		ArrayList<DeliveryOrder> orders = new ArrayList<>();

		DeliveryOrder order = new DeliveryOrder();
		order.setOrderID(ORDER_ID3);
		order.setTotalCost(TOTAL_COST3);
		order.setOrderTimeStamp(ORDER_TIME_STAMP3);
		order.setPaid(IS_PAID3);
		order.setDeliveryTime(DELIVERY_TIME3);

		orders.add(order);

		return orders;
	}


}
