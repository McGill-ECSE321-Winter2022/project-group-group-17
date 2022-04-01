package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dao.ItemRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.InventoryType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;

@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ItemControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        this.itemRepository.deleteAll();
    }

    @AfterEach
    public void cleanup() {
        RestAssuredMockMvc.reset();
    }

    @Test
    public void testGetAllItems() {
        when().get("/items/all").then()
                .statusCode(200)
                .body("$", empty());
    }

    @Test
    public void testCreateAndQueryItemID() {
        final int id = given()
                .param("name", "cheeseburger")
                .param("itemPrice","20")
                .param("inventoryAmount","3")
                .param("isDeliverable","true")
                .param("portionUnit","20g")
                .param("inventoryType","perishable")
                .post("/item/create")
                .then().statusCode(200)
                .body("name", equalTo("cheeseburger"))
                .body("itemPrice", equalTo(20))
                .body("inventoryAmount", equalTo(3))
                .body("portionUnit", equalTo("20g"))
                .body("inventoryType", equalTo(InventoryType.perishable.name()))
                .extract().response().body().path("itemID");
        
        when().get("/item/get/id?id=" +id)
                .then().statusCode(200)
                .body("name", equalTo("cheeseburger"))
                .body("itemPrice", equalTo(20))
                .body("inventoryAmount", equalTo(3))
                .body("portionUnit", equalTo("20g"))
                .body("inventoryType", equalTo(InventoryType.perishable.name()))
                .body("itemID", equalTo(id));

    }
}
