package ca.mcgill.ecse321.grocerystoresystem.service;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.grocerystoresystem.model.ItemQuantity;
import ca.mcgill.ecse321.grocerystoresystem.dao.ItemQuantityRepository;

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
public class TestItemQuantityService {
    @Mock
    private ItemQuantityRepository itemQuantityRepository;

    @InjectMocks
    private ItemQuantityService itemQuantityService;

    private static final int ITEM_ID = 1234;
    private static final int ORDER_ID = 5678;
    private static final int ITEM_QUANTITY_ID = 1357;

    private static final int ITEM_NUM = 5;
    private static final int ITEM_NUM2 = 1;

    @BeforeEach
    public void setMockOutput() {
        lenient().when(itemQuantityRepository.findItemQuantityByQuantityID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ITEM_QUANTITY_ID)) {
                return createItemQuantity();
            } else {
                return null;
            }
        });

        lenient().when(itemQuantityRepository.findItemQuantityByItemNum(anyInt()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if(invocation.getArgument(0).equals(ITEM_NUM2)) {
                        List<ItemQuantity> itemQuantities = new ArrayList<>();
                        ItemQuantity itemQuantity = new ItemQuantity();
                        itemQuantity.setItemNum(ITEM_NUM2);
                        itemQuantities.add(itemQuantity);
                        return itemQuantities;
                    } else {
                        return new ArrayList<>();
                    }

                });

        lenient().when(itemQuantityRepository.findItemQuantitiesByOrderOrderID(anyInt()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if(invocation.getArgument(0).equals(ORDER_ID)) {
                        List<ItemQuantity> itemQuantities = createItemQuantities();
                        return itemQuantities;
                    } else {
                        return new ArrayList<>();
                    }

                });

        lenient().when(itemQuantityRepository.findItemQuantitiesBySpecificItemItemID(anyInt()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if(invocation.getArgument(0).equals(ITEM_ID)) {
                        List<ItemQuantity> itemQuantities = createItemQuantities2();
                        return itemQuantities;
                    } else {
                        return new ArrayList<>();
                    }

                });

        lenient().when(itemQuantityRepository.existsByQuantityID(ITEM_ID)).thenAnswer((InvocationOnMock invocation) ->
                invocation.getArgument(0).equals(ITEM_ID));


        Answer<?> returnParamAsAnswer = (InvocationOnMock invocation) -> {return invocation.getArgument(0);};
        lenient().when(itemQuantityRepository.save(any(ItemQuantity.class))).thenAnswer(returnParamAsAnswer);

    }

    @Test
    public void successfullyCreateItemQuantity() {
        ItemQuantity savedItemQuantity = null;

        try {
            savedItemQuantity = itemQuantityService.createItemQuantity(ITEM_NUM);

        } catch(IllegalArgumentException exp) {
            fail(exp.getMessage());
        }

        assertNotNull(savedItemQuantity);
        assertEquals(ITEM_NUM, savedItemQuantity.getItemNum());

    }

    @Test
    public void unsuccessfullyCreateItemQuantity(){
        ItemQuantity savedItemQuantity = null;
        String error = "";

        try{
            savedItemQuantity = itemQuantityService.createItemQuantity(-1);
        } catch(IllegalArgumentException exp){
            error = exp.getMessage();
        }

        assertNull(savedItemQuantity);
        assertEquals("Input a valid itemNum!", error);
    }

    @Test
    public void successfullyGetItemQuantityByItemQuantityID(){
        ItemQuantity savedItemQuantity = null;

        try{
            savedItemQuantity = itemQuantityService.getItemQuantityWithQuantityID(ITEM_QUANTITY_ID);
        } catch(NullPointerException exp){
            fail(exp.getMessage());
        }

        assertNotNull(savedItemQuantity);
        assertEquals(ITEM_NUM, savedItemQuantity.getItemNum());
    }

    @Test
    public void unsuccessfullyGetItemQuantityByItemQuantityID(){
        ItemQuantity savedItemQuantity = null;
        String error = "";

        try{
            savedItemQuantity = itemQuantityService.getItemQuantityWithQuantityID(01);
        } catch(NullPointerException exp){
            error = exp.getMessage();
        }

        assertNull(savedItemQuantity);
        assertEquals("Item quantity not found", error);
    }

    @Test
    public void successsfullyGetItemQuantitesFromSpecificItemID(){
        List<ItemQuantity> savedItemQuantities = null;

        savedItemQuantities = itemQuantityService.getItemQuantityByItemID(ITEM_ID);

        assertNotNull(savedItemQuantities);
        assertEquals(1, savedItemQuantities.size());

    }

    @Test
    public void successfullyGetItemQuanititesFromOrderID(){
        List<ItemQuantity> savedItemQuantities = null;

        savedItemQuantities = itemQuantityService.getItemQuantityByOrderID(ORDER_ID);

        assertNotNull(savedItemQuantities);
        assertEquals(2, savedItemQuantities.size());
    }

    @Test
    public void successfullyGetItemQuantitesFromItemNum(){
        List<ItemQuantity> savedItemQuantities = null;

        savedItemQuantities = itemQuantityService.getItemQuantityWithNum(ITEM_NUM2);

        assertNotNull(savedItemQuantities);
        assertEquals(1, savedItemQuantities.size());
    }

    private ItemQuantity createItemQuantity(){
        ItemQuantity itemQuantity = new ItemQuantity();
        itemQuantity.setItemNum(ITEM_NUM);
        return itemQuantity;
    }

    private List<ItemQuantity> createItemQuantities(){
        List<ItemQuantity> itemQuantities = new ArrayList<>();

        ItemQuantity item = new ItemQuantity();
        item.setItemNum(ITEM_NUM);

        ItemQuantity item2 = new ItemQuantity();
        item2.setItemNum(ITEM_NUM2);

        itemQuantities.add(item);
        itemQuantities.add(item2);

        return itemQuantities;
    }

    private List<ItemQuantity> createItemQuantities2(){
        List<ItemQuantity> itemQuantities = new ArrayList<>();

        ItemQuantity item2 = new ItemQuantity();
        item2.setItemNum(ITEM_NUM2);

        itemQuantities.add(item2);

        return itemQuantities;
    }
}
