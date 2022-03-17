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

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional

public class AddressController {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @AfterEach
    public void cleanup() {
        RestAssuredMockMvc.reset();
    }
    @Test
    public void testCreateAndGetAllCalendars() {
        post("/address/create");

        post("/address/create");

        post("/address/create");

        post("/address/create");

        post("/address/create");

        post("/address/create");

        when().get("/addresses")
                .then().statusCode(200)
                .body("size()", equalTo(6));
    }
    @Test
    public void testCreateAndGetAndDeleteAddress() {
        final int id = post("/address/create")
                .then().statusCode(200)
                .extract().response().body().path("id");

        when().get("/address/get/id?id="+id)
                .then().statusCode(200);

        String str = when().get("/address/check/id?id="+id)
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");

        String str2 = when().delete("/address/delete?id="+id)
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str2, "true");
    } 
}
