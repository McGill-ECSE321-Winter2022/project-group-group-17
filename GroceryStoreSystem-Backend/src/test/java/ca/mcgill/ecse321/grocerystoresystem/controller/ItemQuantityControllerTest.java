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
public class ItemQuantityControllerTest {
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
                .param("itemNum", "1")
                .post("/itemquantity/create")
                .then().statusCode(200)
                .body("itemNum", equalTo(1))
                .extract().response().body().path("quantityID");

        when().get("/itemquantity/get/id?quantityID=" +id)
                .then().statusCode(200)
                .body("quantityID", equalTo(id))
                .body("itemNum", equalTo(1));


    }

}