package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dao.ItemQuantityRepository;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ItemQuantityController {
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private ItemQuantityRepository itemQuantityRepository;
	
    @BeforeEach
    public void setup() {
    	RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
		this.itemQuantityRepository.deleteAll();

	    }
    @AfterEach
    public void cleanup() {
        RestAssuredMockMvc.reset();
    }
    @Test
    public void testGetNoItemQuantities() {
        when().get("/itemquantities").then()
                .statusCode(200)
                .body("$", empty());
    }
    @Test
    public void testCreateAndQueryItemQuantitiesID() {
    	final int id = given()
    			.param("itemnum", 1)
    			.post("/itemquantity/create")
    			.then().statusCode(200)
    			.body("itemnum", equalTo(1))
    			.extract().response().body().path("id");
    	when().get("/itemquantity/get/id?id="+id)
    		.then().statusCode(200)
    		.body("id", equalTo(id));
    	String str = given()
    			.param("id", id)
    			.get("/itemquantity/check/id")
    			.then().statusCode(200)
    			.extract().response().body().asPrettyString();
    	assertEquals(str, "true");
    }
    @Test
    public void testCreateAndQueryItemQuantitiesItemNum() {
    	 when().delete("/itemquantities/delete").then().statusCode(200);

    	 given()
    	 .param("itemnum", 1)
		 .post("/itemquantity/create")
		 given()
		 .param("itemnum", 1)
		 .post("/itemquantity/create")
		 given()
		 .param("itemnum", 1)
		 .get("/itemquantities/get/itemnum")
		 .then().statusCode(200)
		 .body("size()", equalTo(2));
    	String str = given()
        	.param("itemnum", 1)
       		.get("/address/check/islocal")
      		.then().statusCode(200)
     		.extract().response().body().asPrettyString();
        assertEquals(str,"true");	 		 
    }
	
}
