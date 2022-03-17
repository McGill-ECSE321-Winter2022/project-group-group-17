package ca.mcgill.ecse321.grocerystoresystem.controller;

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

import ca.mcgill.ecse321.grocerystoresystem.dao.AddressRepository;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional

public class AddressController {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AddressRepository addressRepository;
    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        this.addressRepository.deleteAll();
    }

    @AfterEach
    public void cleanup() {
        RestAssuredMockMvc.reset();
    }
    @Test
    public void testGetNoAddresses() {
        when().get("/addresses").then()
                .statusCode(200)
                .body("$", empty());
    }
    @Test
    public void testCreateAndQueryAddressID() {
    	final int id = given()
    	.param("streetname", "Peel")
    	.param("streetnum", "1234")
    	.param("city", "Montreal")
    	.param("postalcode","HHHHHH")
    	.param("country","Canada")
    	.param("isLocal",true)
    	.post("/address/create")
    	.then().statusCode(200)
    	.body("streetname", equalTo("Peel"))
    	.body("streetnum", equalTo("1234"))
    	.body("city", equalTo("Montreal"))
    	.body("postalcode",equalTo("HHHHHH"))
    	.body("country", equalTo("Canada"))
    	.body("isLocal", equalTo(true))
    	.extract().response().body().path("id");
    	
    when().get("/address/get/id?id="+id)
    	.then().statusCode(200)
    	.body("id", equalTo(id));
    String str = given()
    		.param("id", id)
    		.get("/address/check/id/")
    		.then().statusCode(200)
    		.extract().response().body().asPrettyString();
    assertEquals(str, "true");
    }
    @Test
    public void testCreateAndQueryAddressStreetName() {
    	when().delete("/addresses/delete").then().statusCode(200);
    	
    	given()
    	.param("streetname", "Peel")
    	.param("streetnum", "1234")
    	.param("city", "Montreal")
    	.param("postalcode","HHHHHH")
    	.param("country","Canada")
    	.param("isLocal",true)
    	.post("/address/create")
    	given()
    	.param("streetname", "Peel")
    	.param("streetnum", "1235")
    	.param("city", "Montreal")
    	.param("postalcode","HHH4HH")
    	.param("country","Canada")
    	.param("isLocal",true)
    	.post("/address/create")
    	given()
    	.param("streetname", "Peel")
    	.get("/address/get/streetname/")
    	.then().statusCode(200)
    	.body("size()", equalTo(2));
    	String str = given()
    			.param("streetname", "Peel")
    			.get("/address/check/streetname")
    			.then().statusCode(200)
    			.extract().response().body().asPrettyString();
    	assertEquals(str,"true");	
    }
    @Test
    public void testCreateAndQueryAddressStreetNum() {
    	when().delete("/addresses/delete").then().statusCode(200);
    	
    	given()
    	.param("streetname", "Peel")
    	.param("streetnum", "1234")
    	.param("city", "Montreal")
    	.param("postalcode","HHHHHH")
    	.param("country","Canada")
    	.param("isLocal",true)
    	.post("/address/create")
    	given()
    	.param("streetname", "Peel")
    	.param("streetnum", "1234")
    	.param("city", "Montreal")
    	.param("postalcode","HHH4HH")
    	.param("country","Canada")
    	.param("isLocal",true)
    	.post("/address/create")
    	given()
    	.param("streetnum", "1234")
    	.get("/address/get/streetnum/")
    	.then().statusCode(200)
    	.body("size()", equalTo(2));
    	String str = given()
    			.param("streetnum", "1234")
    			.get("/address/check/streetnum")
    			.then().statusCode(200)
    			.extract().response().body().asPrettyString();
    	assertEquals(str,"true");	
    }
    @Test
    public void testCreateAndQueryAddressCity() {
    	when().delete("/addresses/delete").then().statusCode(200);
    	
    	given()
    	.param("streetname", "Monkland")
    	.param("streetnum", "1234")
    	.param("city", "Montreal")
    	.param("postalcode","HHHHHH")
    	.param("country","Canada")
    	.param("isLocal",true)
    	.post("/address/create")
    	given()
    	.param("streetname", "Peel")
    	.param("streetnum", "1235")
    	.param("city", "Montreal")
    	.param("postalcode","HHH4HH")
    	.param("country","Canada")
    	.param("isLocal",true)
    	.post("/address/create")
    	given()
    	.param("city", "Montreal")
    	.get("/address/get/city/")
    	.then().statusCode(200)
    	.body("size()", equalTo(2));
    	String str = given()
    			.param("city", "Montreal")
    			.get("/address/check/city")
    			.then().statusCode(200)
    			.extract().response().body().asPrettyString();
    	assertEquals(str,"true");	
    }
    @Test
    public void testCreateAndQueryAddressPostalCode() {
    	when().delete("/addresses/delete").then().statusCode(200);
    	
    	given()
    	.param("streetname", "Peel")
    	.param("streetnum", "1234")
    	.param("city", "Montreal")
    	.param("postalcode","HHHHHH")
    	.param("country","Canada")
    	.param("isLocal",true)
    	.post("/address/create")
    	given()
    	.param("streetname", "Peel")
    	.param("streetnum", "1235")
    	.param("city", "Montreal")
    	.param("postalcode","HHHHHH")
    	.param("country","Canada")
    	.param("isLocal",true)
    	.post("/address/create")
    	given()
    	.param("postalcode", "HHHHHH")
    	.get("/address/get/postalcode/")
    	.then().statusCode(200)
    	.body("size()", equalTo(2));
    	String str = given()
    			.param("postalcode", "HHHHHH")
    			.get("/address/check/postalcode")
    			.then().statusCode(200)
    			.extract().response().body().asPrettyString();
    	assertEquals(str,"true");	
    }
    @Test
    public void testCreateAndQueryAddressCountry() {
    	when().delete("/addresses/delete").then().statusCode(200);
    	
    	given()
    	.param("streetname", "Peel")
    	.param("streetnum", "1234")
    	.param("city", "Montreal")
    	.param("postalcode","HHH3HH")
    	.param("country","Canada")
    	.param("isLocal",true)
    	.post("/address/create")
    	given()
    	.param("streetname", "Peel")
    	.param("streetnum", "1235")
    	.param("city", "Montreal")
    	.param("postalcode","HHHHHH")
    	.param("country","Canada")
    	.param("isLocal",true)
    	.post("/address/create")
    	given()
    	.param("country", "Canada")
    	.get("/address/get/country/")
    	.then().statusCode(200)
    	.body("size()", equalTo(2));
    	String str = given()
    			.param("country", "Canada")
    			.get("/address/check/country")
    			.then().statusCode(200)
    			.extract().response().body().asPrettyString();
    	assertEquals(str,"true");	
    }
    @Test
    public void testCreateAndQueryAddressIsLocal() {
    	when().delete("/addresses/delete").then().statusCode(200);
    	
    	given()
    	.param("streetname", "Peel")
    	.param("streetnum", "1234")
    	.param("city", "Montreal")
    	.param("postalcode","HHH3HH")
    	.param("country","Canada")
    	.param("isLocal",true)
    	.post("/address/create")
    	given()
    	.param("streetname", "Peel")
    	.param("streetnum", "1235")
    	.param("city", "Montreal")
    	.param("postalcode","HHHHHH")
    	.param("country","Canada")
    	.param("isLocal",true)
    	.post("/address/create")
    	given()
    	.param("islocal", true)
    	.get("/address/get/islocal/")
    	.then().statusCode(200)
    	.body("size()", equalTo(2));
    	String str = given()
    			.param("islocal", true)
    			.get("/address/check/islocal")
    			.then().statusCode(200)
    			.extract().response().body().asPrettyString();
    	assertEquals(str,"true");	
    }
    
}

