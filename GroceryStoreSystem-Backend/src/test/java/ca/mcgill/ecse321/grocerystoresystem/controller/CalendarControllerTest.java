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

@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CalendarControllerTest {
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

    }

    @Test
    public void testCreateAndGetAndDeleteCalendar() {

    }

    @Test
    public void testCreateAndGetAndDeleteAllCalendars() {

    }
}
