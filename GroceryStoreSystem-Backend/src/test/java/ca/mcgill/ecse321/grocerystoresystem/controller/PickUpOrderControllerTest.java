package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dao.OrderRepository;

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
public class PickUpOrderControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        this.orderRepository.deleteAll();
    }

    @AfterEach
    public void cleanup() {
        RestAssuredMockMvc.reset();
    }

    @Test
    public void testGetNoPickUpOrders() {
        when().get("/pickuporders").then()
                .statusCode(200)
                .body("$", empty());
    }

    @Test
    public void testCreateAndQueryPickUpOrderID() {
        final int id = given()
                .param("totalCost", 1000)
                .param("orderTimeStamp", "2022.01.02/12.12.12")
                .param("isPaid", "true")
                .param("pickupDateTime", "2022.01.02/16.12.12")
                .post("/pickuporder/create/")
                .then().statusCode(200)
                .body("totalCost", equalTo(1000))
                .body("orderTimeStamp", equalTo("2022-01-02T12:12:12"))
                .body("paid", equalTo(true))
                .body("pickupTime", equalTo("2022-01-02T16:12:12"))
                .extract().response().body().path("id");

        when().get("/pickuporder/get/id?id="+id)
                .then().statusCode(200)
                .body("id", equalTo(id))
                .body("totalCost", equalTo(1000))
                .body("orderTimeStamp", equalTo("2022-01-02T12:12:12"))
                .body("paid", equalTo(true))
                .body("pickupTime", equalTo("2022-01-02T16:12:12"));

    }


}
