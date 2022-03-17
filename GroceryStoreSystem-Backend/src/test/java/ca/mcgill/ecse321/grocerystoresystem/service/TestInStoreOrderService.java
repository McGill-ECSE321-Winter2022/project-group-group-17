package ca.mcgill.ecse321.grocerystoresystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.grocerystoresystem.model.DeliveryOrder;
import ca.mcgill.ecse321.grocerystoresystem.model.InStoreOrder;
import ca.mcgill.ecse321.grocerystoresystem.dao.InStoreOrderRepository;

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
public class TestInStoreOrderService {
	@Mock 
	private InStoreOrderRepository inStoreOrderRepository;
	
	@InjectMocks
	private InStoreOrderService inStoreOrderService;
	
	private static final int ORDER_ID = 1234;
	private static final int TOTAL_COST = 1000;
	private static final LocalDateTime ORDER_TIME_STAMP = LocalDateTime.of(2022, 01,01,01,01,01,01);
	private static final boolean IS_PAID = true;

	private static final int ORDER_ID2 = 1000;
	private static final int TOTAL_COST2 = 1234;
	private static final LocalDateTime ORDER_TIME_STAMP2 = LocalDateTime.of(2022, 02,01,01,01,01,01);
	private static final boolean IS_PAID2 = true;

	private static final int PERSON_KEY = 1002;

	@BeforeEach
	public void setMockOutput() {
		lenient().when(inStoreOrderRepository.findInStoreOrderByOrderID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ORDER_ID)) {
                return createInStoreOrder();
            } else {
                return null;
            }
        });

		lenient().when(inStoreOrderRepository.findInStoreOrderByPersonPersonID(anyInt()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if(invocation.getArgument(0).equals(PERSON_KEY)) {
						List<InStoreOrder> orders = createInStoreOrders();
						return orders;
					} else {
						return new ArrayList<>();
					}

				});
	
		lenient().when(inStoreOrderRepository.existsByOrderID(ORDER_ID)).thenAnswer((InvocationOnMock invocation) -> 
		invocation.getArgument(0).equals(ORDER_ID));
		
		
		 Answer<?> returnParamAsAnswer = (InvocationOnMock invocation) -> {return invocation.getArgument(0);};
		 lenient().when(inStoreOrderRepository.save(any(InStoreOrder.class))).thenAnswer(returnParamAsAnswer);
	}
	
	@Test
	 public void successfullyCreateInStoreOrder() {
		 InStoreOrder savedOrder = null;
		 
		 try {
			savedOrder = inStoreOrderService.createInStoreOrder(TOTAL_COST, 
	        		ORDER_TIME_STAMP, IS_PAID);
	        
		 } catch(IllegalArgumentException exp) {
			 fail(exp.getMessage());
		 }
	        
	     assertNotNull(savedOrder);
	     assertEquals(TOTAL_COST, savedOrder.getTotalCost());
	     assertEquals(ORDER_TIME_STAMP, savedOrder.getOrderTimeStamp());
	     assertEquals(IS_PAID, savedOrder.isPaid());
	       
	    }
	
	@Test
	 public void unsuccessfullyCreateInStoreOrderWithNegativeTotalCost() {
		 InStoreOrder savedOrder = null;
		 String error = "";
		 
		 try {
			 savedOrder = inStoreOrderService.createInStoreOrder(-1000, ORDER_TIME_STAMP, 
					 IS_PAID);
		 } catch (IllegalArgumentException exception) {
			 error = exception.getMessage();
		 }
		 
		 assertNull(savedOrder);
		 assertEquals("Please submit a valid totalCost", error);
	 }

	@Test
	public void successfullyGetInStoreOrderByID() {
		InStoreOrder inStoreOrder = null;
		
		try {
			inStoreOrder = inStoreOrderService.getInStoreOrderByID(ORDER_ID);
		} catch(NullPointerException exception) {
			fail(exception.getMessage());
		}
		
		assertNotNull(inStoreOrder);
		assertEquals(TOTAL_COST, inStoreOrder.getTotalCost());
		assertEquals(ORDER_TIME_STAMP, inStoreOrder.getOrderTimeStamp());
		assertEquals(IS_PAID, inStoreOrder.isPaid());
	}
	
	@Test
	public void unsuccessfullyGetInStoreOrderByID() {
		InStoreOrder inStoreOrder = null;
		String error = "";
		
		try {
			inStoreOrder = inStoreOrderService.getInStoreOrderByID(01234);
		} catch(NullPointerException exception) {
			error = exception.getMessage();
		}
		
		assertNull(inStoreOrder);
		assertEquals("Order was not found", error);
	}
	
	@Test
	public void unsuccessfullyDeleteInStoreOrder() {
		InStoreOrder order = new InStoreOrder();
		order.setOrderID(123);
		boolean deleted = false;
		String error = "";
		
		try {
			deleted = inStoreOrderService.deleteInStoreOrderWithID(012);

		} catch (NullPointerException exception) {
			error = exception.getMessage();
		}
		
		assertFalse(deleted);
		assertNotNull(order);
		assertEquals("Order was not found", error);
		
		
	}

	private InStoreOrder createInStoreOrder() {
		InStoreOrder inStoreOrder = new InStoreOrder();
		inStoreOrder.setOrderID(ORDER_ID);
		inStoreOrder.setTotalCost(TOTAL_COST);
		inStoreOrder.setOrderTimeStamp(ORDER_TIME_STAMP);
		inStoreOrder.setPaid(IS_PAID);
		return inStoreOrder;
	}

	private List<InStoreOrder> createInStoreOrders(){
		List<InStoreOrder> orders = new ArrayList<>();

		InStoreOrder order = new InStoreOrder();
		order.setOrderID(ORDER_ID);
		order.setTotalCost(TOTAL_COST);
		order.setOrderTimeStamp(ORDER_TIME_STAMP);
		order.setPaid(IS_PAID);


		InStoreOrder order2 = new InStoreOrder();
		order2.setOrderID(ORDER_ID2);
		order2.setTotalCost(TOTAL_COST2);
		order2.setOrderTimeStamp(ORDER_TIME_STAMP2);
		order2.setPaid(IS_PAID2);

		orders.add(order);
		orders.add(order2);

		return orders;
	}

}
