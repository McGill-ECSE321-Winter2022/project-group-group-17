package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dao.CalendarRepository;
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
public class CalendarControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CalendarRepository calendarRepository;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        this.calendarRepository.deleteAll();
    }

    @AfterEach
    public void cleanup() {
        RestAssuredMockMvc.reset();
    }

    @Test
    public void testCreateAndGetAllCalendars() {
        post("/calendar/create");

        post("/calendar/create");

        post("/calendar/create");

        post("/calendar/create");

        post("/calendar/create");

        post("/calendar/create");

        when().get("/calendars")
                .then().statusCode(200)
                .body("size()", equalTo(6));
    }

    @Test
    public void testCreateAndGetAndDeleteCalendar() {
        final int id = post("/calendar/create")
                .then().statusCode(200)
                .extract().response().body().path("id");

        when().get("/calendar/get/id?id="+id)
                .then().statusCode(200);

        String str = when().get("/calendar/check/id?id="+id)
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");

        String str2 = when().delete("/calendar/delete?id="+id)
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str2, "true");
    }
}
