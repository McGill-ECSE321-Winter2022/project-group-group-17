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
public class AddressControllerTest {
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
                .param("streetName", "Peel")
                .param("streetNum", "1234")
                .param("city", "Montreal")
                .param("postalCode","HHHHHH")
                .param("country","Canada")
                .param("isLocal","true")
                .post("/address/create")
                .then().statusCode(200)
                .body("streetName", equalTo("Peel"))
                .body("streetNum", equalTo("1234"))
                .body("city", equalTo("Montreal"))
                .body("postalCode",equalTo("HHHHHH"))
                .body("country", equalTo("Canada"))
                .body("local", equalTo(true))
                .extract().response().body().path("id");

        when().get("/address/get/id?id="+id)
                .then().statusCode(200)
                .body("id", equalTo(id))
                .body("streetName", equalTo("Peel"))
                .body("streetNum", equalTo("1234"))
                .body("city", equalTo("Montreal"))
                .body("postalCode", equalTo("HHHHHH"))
                .body("country", equalTo("Canada"))
                .body("local", equalTo(true));

    }

    @Test
    public void testCreateAndQueryAddressStreetName() {
        when().delete("/addresses/delete").then().statusCode(200);

        given()
                .param("streetName", "Peel")
                .param("streetNum", "1234")
                .param("city", "Montreal")
                .param("postalCode","HHHHHH")
                .param("country","Canada")
                .param("isLocal",true)
                .post("/address/create");
        given()
                .param("streetName", "Peel")
                .param("streetNum", "1235")
                .param("city", "Montreal")
                .param("postalCode","HHH4HH")
                .param("country","Canada")
                .param("isLocal",true)
                .post("/address/create");
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

}