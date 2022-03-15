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

import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.Matchers.empty;

@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class OwnerControllerTest {
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
    public void testGetNoOwners() {
        when().get("/owners").then()
                .statusCode(200)
                .body("$", empty());
    }

    @Test
    public void testGetOwners() {

    }

    @Test
    public void testCreateAndQueryOwnerID() {

    }

    @Test
    public void testCreateAndQueryOnwerFirstName() {

    }

    @Test
    public void testCreateAndQueryOwnerLastName() {

    }

    @Test
    public void testCreateAndQueryOwnerFullName() {

    }

    @Test
    public void testCreateAndQueryOwnerEmail() {

    }

    @Test
    public void testCreateAndDeleteOwner() {

    }

    @Test
    public void testCreateAndDeleteMultipleOwners() {

    }

    @Test
    public void testCreateRetrieveAndDeleteOwner() {

    }

    @Test
    public void testCreateAndUpdateAddress() {

    }

    @Test
    public void testCreateAndUpdatePassword() {

    }

}
