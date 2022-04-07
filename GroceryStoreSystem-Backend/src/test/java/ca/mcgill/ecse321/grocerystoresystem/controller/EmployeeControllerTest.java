package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dao.PersonRepository;
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
public class EmployeeControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        this.personRepository.deleteAll();

    }

    @AfterEach
    public void cleanup() {
        RestAssuredMockMvc.reset();
    }

    @Test
    public void testGetNoEmployees() {
        when().get("/employees").then()
                .statusCode(200)
                .body("$", empty());
    }

    @Test
    public void testCreateAndQueryEmployeeID() {
        final int id = given()
                .param("firstname", "Amy")
                .param("lastname", "Wang")
                .param("email", "amywang02@gmail.com")
                .param("password", "12345678")
                .param("empStatus", "hired")
                .post("/employee/create")
                .then().statusCode(200)
                .body("firstName", equalTo("Amy"))
                .body("lastName", equalTo("Wang"))
                .body("email", equalTo("amywang02@gmail.com"))
                .body("status", equalTo("hired"))
                .extract().response().body().path("id");

        when().get("/employee/get/id?id="+id)
                .then().statusCode(200)
                .body("id", equalTo(id));

        String str =  given()
                .param("id", id)
                .get("/employee/check/id/")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");
    }

    @Test
    public void testCreateAndQueryEmployeeFirstName() {
        when().delete("/employees/delete").then().statusCode(200);

        given()
                .param("firstname", "Amy")
                .param("lastname", "Wang")
                .param("email", "amywang02@gmail.com")
                .param("password", "12345678")
                .param("empStatus", "hired")
                .post("/employee/create");

        given()
                .param("firstname", "Amy")
                .param("lastname", "Wang2")
                .param("email", "amywang03@gmail.com")
                .param("password", "12345678")
                .param("empStatus", "hired")
                .post("/employee/create");

        given()
                .param("firstname", "Amy")
                .get("/employee/get/firstname/")
                .then().statusCode(200)
                .body("size()", equalTo(2));

        String str =  given()
                .param("firstname", "Amy")
                .get("/employee/check/firstname/")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");
    }

    @Test
    public void testCreateAndQueryEmployeeLastName() {

        when().delete("/employees/delete").then().statusCode(200);

        given()
                .param("firstname", "Amy")
                .param("lastname", "Wang")
                .param("email", "amywang02@gmail.com")
                .param("password", "12345678")
                .param("empStatus", "hired")
                .post("/employee/create");

        given()
                .param("firstname", "Amy")
                .param("lastname", "Wang")
                .param("email", "amywang03@gmail.com")
                .param("password", "12345678")
                .param("empStatus", "hired")
                .post("/employee/create");

        given()
                .param("lastname", "Wang")
                .get("/employee/get/lastname/")
                .then().statusCode(200)
                .body("size()", equalTo(2));

        String str =  given()
                .param("lastname", "Wang")
                .get("/employee/check/lastname/")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");
    }

    @Test
    public void testCreateAndQueryEmployeeFullName() {
        when().delete("/employees/delete").then().statusCode(200);

        given()
                .param("firstname", "Amy")
                .param("lastname", "Wang")
                .param("email", "amywang02@gmail.com")
                .param("password", "12345678")
                .param("empStatus", "hired")
                .post("/employee/create");

        given()
                .param("firstname", "Amy")
                .param("lastname", "Wang")
                .param("email", "amywang03@gmail.com")
                .param("password", "12345678")
                .param("empStatus", "hired")
                .post("/employee/create");

        given()
                .param("firstname", "Amy")
                .param("lastname", "Wang")
                .get("/employee/get/fullname/")
                .then().statusCode(200)
                .body("size()", equalTo(2));

        String str =  given()
                .param("firstname", "Amy")
                .param("lastname", "Wang")
                .get("/employee/check/fullname/")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");
    }

    @Test
    public void testCreateAndQueryEmployeeEmail() {
        final int id = given()
                .param("firstname", "Amy")
                .param("lastname", "Wang")
                .param("email", "amywang02@gmail.com")
                .param("password", "12345678")
                .param("empStatus", "hired")
                .post("/employee/create").then()
                .statusCode(200)
                .extract().response().body().path("id");

        given()
                .param("email", "amywang02@gmail.com")
                .get("/employee/get/email/")
                .then().statusCode(200)
                .body("firstName", equalTo("Amy"))
                .body("lastName", equalTo("Wang"))
                .body("id", equalTo(id));

        String str =  given()
                .param("email", "amywang02@gmail.com")
                .get("/employee/check/email/")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");
    }

    @Test
    public void testCreateGetAndDeleteEmployee() {
        final int id = given()
                .param("firstname", "Amy")
                .param("lastname", "Wang")
                .param("email", "amywang02@gmail.com")
                .param("password", "12345678")
                .param("empStatus", "hired")
                .post("/employee/create").then()
                .statusCode(200)
                .extract().response().body().path("id");

        when().get("/employee/get/id?id="+id)
                .then().statusCode(200)
                .body("id", equalTo(id));

        String str =  given()
                .param("id", id)
                .get("/employee/check/id/")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");

        String str2 = when().delete("/employee/delete?id="+id)
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, str2);
    }

    @Test
    public void testCreateAndDeleteMultipleEmployees() {

        when().delete("/employees/delete").then().statusCode(200);

        given()
                .param("firstname", "Amy")
                .param("lastname", "Wang")
                .param("email", "amywang02@gmail.com")
                .param("password", "12345678")
                .param("empStatus", "hired")
                .post("/employee/create").then()
                .statusCode(200);

        given()
                .param("firstname", "Amy")
                .param("lastname", "Wang")
                .param("email", "amywang03@gmail.com")
                .param("password", "12345678")
                .param("empStatus", "hired")
                .post("/employee/create").then()
                .statusCode(200);

        given()
                .param("firstname", "Amy")
                .param("lastname", "Wang")
                .param("email", "amywang04@gmail.com")
                .param("password", "12345678")
                .param("empStatus", "hired")
                .post("/employee/create").then()
                .statusCode(200);

        when().get("/employees")
                .then().statusCode(200)
                .body("size()", equalTo(3));

        String str = when().delete("/employees/delete/")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");

        when().get("/employees")
                .then().statusCode(200)
                .body("size()", equalTo(0));

    }

    @Test
    public void testCreateAndUpdateAddress() {
        final int id = given()
                .param("firstname", "Amy")
                .param("lastname", "Wang")
                .param("email", "amywang02@gmail.com")
                .param("password", "12345678")
                .param("empStatus", "hired")
                .post("/employee/create").then()
                .statusCode(200)
                .extract().response().body().path("id");

        final int addressID = given()
                .param("streetName", "Drummond")
                .param("streetNum", "1233")
                .param("city", "Montreal")
                .param("postalCode", "H3VC1D")
                .param("country", "Canada")
                .param("isLocal", false)
                .post("/address/create/")
                .then().statusCode(200)
                .extract().response().body().path("id");

        given()
                .param("id", id)
                .param("addressID", addressID)
                .post("/employee/update/address/")
                .then().statusCode(200)
                .extract().response().body().prettyPrint();
    }
}
