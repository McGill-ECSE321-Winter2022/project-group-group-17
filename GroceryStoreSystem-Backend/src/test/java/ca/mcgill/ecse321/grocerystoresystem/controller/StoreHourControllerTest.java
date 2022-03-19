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

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional

public class StoreHourControllerTest {

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
    public void testGetNoShift() {
        when().get("/storehours").then()
                .statusCode(200)
                .body("size()", equalTo(0));
    }

    @Test
    public void testCreateAndGetById() {

        final int id = given()
                .param("startTime", "09:00:00")
                .param("endTime", "21:00:00")
                .param("weekday", "Monday")
                .post("/storehour/create")
                .then().statusCode(400)
                .body("startTime", equalTo("09:00:00"))
                .extract().response().body().path("id");

        when().get("/storehour/get/id?id=" + id).then()
                .statusCode(400)
                .body("id", equalTo(id))
                .body("starttime", equalTo("09:00:00"))
                .body("endtime", equalTo("21:00:00"))
                .body("weekday", equalTo("Monday"));

        String str = when().get("/storehour/check/id?id="+id).then()
                .statusCode(400)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");

    }


}
