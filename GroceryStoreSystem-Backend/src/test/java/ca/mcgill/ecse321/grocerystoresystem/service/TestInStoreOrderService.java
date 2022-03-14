package ca.mcgill.ecse321.grocerystoresystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(inStoreOrderRepository.findInStoreOrderByOrderID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ORDER_ID)) {
                return createInStoreOrder();
            } else {
                return null;
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
	
	private InStoreOrder createInStoreOrder() {
		InStoreOrder inStoreOrder = new InStoreOrder();
		inStoreOrder.setOrderID(ORDER_ID);
		inStoreOrder.setTotalCost(TOTAL_COST);
		inStoreOrder.setOrderTimeStamp(ORDER_TIME_STAMP);
		inStoreOrder.setPaid(IS_PAID);
		return inStoreOrder;
	}
	
	

}
