package ca.mcgill.ecse321.grocerystoresystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.grocerystoresystem.dao.PickupOrderRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.PickupOrder;

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
public class TestPickupOrderService {
	
	@Mock 
	private PickupOrderRepository pickupOrderRepository;
	
	@InjectMocks
	private PickupOrderService pickupOrderService;
	
	private static final int ORDER_ID = 1234;
	private static final int TOTAL_COST = 1000;
	private static final LocalDateTime ORDER_TIME_STAMP = LocalDateTime.of(2022, 01,01,01,01,01,01);
	private static final boolean IS_PAID = true;
	private static final LocalDateTime PICKUP_TIME = LocalDateTime.of(2022,01,02,01,01,01,01);

	private static final int ORDER_ID2 = 1000;
	private static final int TOTAL_COST2 = 1234;
	private static final LocalDateTime ORDER_TIME_STAMP2 = LocalDateTime.of(2022, 02,01,01,01,01,01);
	private static final boolean IS_PAID2 = true;
	private static final LocalDateTime PICKUP_TIME2 = LocalDateTime.of(2022,02,02,01,01,01,01);

	private static final int ORDER_ID3 = 1111;
	private static final int TOTAL_COST3 = 3404;
	private static final LocalDateTime ORDER_TIME_STAMP3 = LocalDateTime.of(2022, 03,01,01,01,01,01);
	private static final boolean IS_PAID3 = true;
	private static final LocalDateTime PICKUP_TIME3 = LocalDateTime.of(2022,03,02,01,01,01,01);


	private static final int PERSON_KEY = 1002;

	
	@BeforeEach
	public void setMockOutput() {
		
		 lenient().when(pickupOrderRepository.findPickupOrderByOrderID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	            if(invocation.getArgument(0).equals(ORDER_ID)) {
	                return createPickupOrder();
	            } else {
	                return null;
	            }
	        });


		lenient().when(pickupOrderRepository.findPickupOrderByPersonPersonID(anyInt()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if(invocation.getArgument(0).equals(PERSON_KEY)) {
						List<PickupOrder> orders = createPickupOrders2();
						return orders;
					} else {
						return new ArrayList<>();
					}

				});

		lenient().when(pickupOrderRepository.existsByOrderID(ORDER_ID)).thenAnswer((InvocationOnMock invocation) -> 
		invocation.getArgument(0).equals(ORDER_ID));
		
		
		 Answer<?> returnParamAsAnswer = (InvocationOnMock invocation) -> {return invocation.getArgument(0);};
		 lenient().when(pickupOrderRepository.save(any(PickupOrder.class))).thenAnswer(returnParamAsAnswer);
	}
	
	 @Test
	 public void successfullyCreatePickupOrder() {
		 PickupOrder savedOrder = null;
		 
		 try {
			savedOrder = pickupOrderService.createPickupOrder(TOTAL_COST, 
	        		ORDER_TIME_STAMP, IS_PAID, PICKUP_TIME);
	        
		 } catch(IllegalArgumentException exp) {
			 fail(exp.getMessage());
		 }
	        
	     assertNotNull(savedOrder);
	     assertEquals(TOTAL_COST, savedOrder.getTotalCost());
	     assertEquals(ORDER_TIME_STAMP, savedOrder.getOrderTimeStamp());
	     assertEquals(IS_PAID, savedOrder.isPaid());
	     assertEquals(PICKUP_TIME, savedOrder.getPickupDate());   
	    }
	 
	 @Test
	 public void unsuccessfullyCreatePickupOrderWithNegativeTotalCost() {
		 PickupOrder savedOrder = null;
		 String error = "";
		 
		 try {
			 savedOrder = pickupOrderService.createPickupOrder(-1000, ORDER_TIME_STAMP, 
					 IS_PAID, PICKUP_TIME);
		 } catch (IllegalArgumentException exception) {
			 error = exception.getMessage();
		 }
		 
		 assertNull(savedOrder);
		 assertEquals("Please submit a valid total cost", error);
	 }

	 @Test
	 public void successfullyGetPickupOrdersWithPerson(){
		 List<PickupOrder> savedOrder = null;

		 savedOrder = pickupOrderService.getAllPickupOrdersOfPersonWithPersonID(PERSON_KEY);

		 assertNotNull(savedOrder);
		 assertEquals(1, savedOrder.size());
	 }



	@Test
	public void successfullyGetPickupOrderByID() {
		PickupOrder pickupOrder = null;

		try {
			pickupOrder = pickupOrderService.getPickupOrderByID(ORDER_ID);
		} catch(NullPointerException exception) {
			fail(exception.getMessage());
		}

		assertNotNull(pickupOrder);
		assertEquals(TOTAL_COST, pickupOrder.getTotalCost());
		assertEquals(ORDER_TIME_STAMP, pickupOrder.getOrderTimeStamp());
		assertEquals(IS_PAID, pickupOrder.isPaid());
		assertEquals(PICKUP_TIME, pickupOrder.getPickupDate());
	}
	
	private PickupOrder createPickupOrder() {
		PickupOrder pickupOrder = new PickupOrder();
		pickupOrder.setOrderID(ORDER_ID);
		pickupOrder.setTotalCost(TOTAL_COST);
		pickupOrder.setOrderTimeStamp(ORDER_TIME_STAMP);
		pickupOrder.setPaid(IS_PAID);
		pickupOrder.setPickupDate(PICKUP_TIME);
		return pickupOrder;
	}
		
	
	private List<PickupOrder> createPickupOrders(){
		ArrayList<PickupOrder> orders = new ArrayList<>();

		PickupOrder order = new PickupOrder();
		order.setOrderID(ORDER_ID);
		order.setTotalCost(TOTAL_COST);
		order.setOrderTimeStamp(ORDER_TIME_STAMP);
		order.setPaid(IS_PAID);
		order.setPickupDate(PICKUP_TIME);

		PickupOrder order2 = new PickupOrder();
		order2.setOrderID(ORDER_ID2);
		order2.setTotalCost(TOTAL_COST2);
		order2.setOrderTimeStamp(ORDER_TIME_STAMP2);
		order2.setPaid(IS_PAID2);
		order2.setPickupDate(PICKUP_TIME2);

		orders.add(order);
		orders.add(order2);

		return orders;
	}


	private List<PickupOrder> createPickupOrders2(){
		ArrayList<PickupOrder> orders = new ArrayList<>();

		PickupOrder order = new PickupOrder();
		order.setOrderID(ORDER_ID3);
		order.setTotalCost(TOTAL_COST3);
		order.setOrderTimeStamp(ORDER_TIME_STAMP3);
		order.setPaid(IS_PAID3);
		order.setPickupDate(PICKUP_TIME3);

		orders.add(order);

		return orders;
	}


}
