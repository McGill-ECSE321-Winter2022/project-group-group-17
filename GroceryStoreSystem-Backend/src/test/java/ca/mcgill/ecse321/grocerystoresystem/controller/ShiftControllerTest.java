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
public class ShiftControllerTest {
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
        when().get("/shifts").then()
                .statusCode(200)
                .body("size()", equalTo(0));
    }

    @Test
    public void testCreateAndGetById() {
        final int id = given()
                .param("date", "2022.01.02")
                .param("starttime", "08.30.00")
                .param("endtime", "17.00.00")
                .param("status", "assigned")
                .post("/shift/create")
                .then().statusCode(200)
                .body("date", equalTo("2022-01-02"))
                .extract().response().body().path("id");

        when().get("/shift/get/id?id=" + id).then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("date", equalTo("2022-01-02"))
                .body("startTime", equalTo("08:30:00"))
                .body("endTime", equalTo("17:00:00"))
                .body("shiftStatus", equalTo("assigned"));

        String str = when().get("/shift/check/id?id="+id).then()
                .statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");

    }

    @Test
    public void testCreateAndGetByDate() {
        given()
                .param("date", "2022.01.02")
                .param("starttime", "08.30.00")
                .param("endtime", "17.00.00")
                .param("status", "assigned")
                .post("/shift/create");
        given()
                .param("date", "2022.01.02")
                .param("starttime", "10.00.00")
                .param("endtime", "17.00.00")
                .param("status", "assigned")
                .post("/shift/create");


        given()
                .param("date", "2022.01.02")
                .get("/shift/get/date")
                .then().statusCode(200)
                .body("size()", equalTo(2));

        String str = given()
                .param("date", "2022.01.02")
                .get("/shift/check/date")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");

    }

    @Test
    public void testCreateAndGetByStatus() {
        given()
                .param("date", "2022.01.02")
                .param("starttime", "08.30.00")
                .param("endtime", "17.00.00")
                .param("status", "assigned")
                .post("/shift/create");
        given()
                .param("date", "2022.01.02")
                .param("starttime", "10.00.00")
                .param("endtime", "17.00.00")
                .param("status", "assigned")
                .post("/shift/create");

        given()
                .param("status", "assigned")
                .get("/shift/get/status")
                .then().statusCode(200)
                .body("size()", equalTo(2));

        String str = given()
                .param("status", "assigned")
                .get("/shift/check/status")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");
    }

    @Test
    public void testCreateAndGetByEmployee() {
        final int id = given()
                .param("date", "2022.01.02")
                .param("starttime", "08.30.00")
                .param("endtime", "17.00.00")
                .param("status", "assigned")
                .post("/shift/create")
                .then().statusCode(200)
                .extract().response().body().path("id");


        final int employeeID = given()
                .param("firstname", "Mario")
                .param("lastname", "Bouzakhm")
                .param("email", "mariobouzakhm03@gmail.com")
                .param("password", "12345678")
                .param("empStatus", "hired")
                .post("/employee/create")
                .then().statusCode(200)
                .extract().response().body().path("id");


        given()
                .param("shiftID", id)
                .param("employeeID", employeeID)
                .post("/shift/update/employee")
                .then().statusCode(200);

        given()
                .param("id", employeeID)
                .get("/shift/get/employee")
                .then().statusCode(200)
                .body("[0].id", equalTo(id))
                .body("[0].date", equalTo("2022-01-02"));

        String str = given()
                .param("id", employeeID)
                .get("/shift/check/employee")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");
    }

    @Test
    public void testCreateAndGetByDateAndTimes() {
        given()
                .param("date", "2022.01.02")
                .param("starttime", "10.00.00")
                .param("endtime", "17.00.00")
                .param("status", "assigned")
                .post("/shift/create");
        given()
                .param("date", "2022.01.02")
                .param("starttime", "10.00.00")
                .param("endtime", "17.00.00")
                .param("status", "assigned")
                .post("/shift/create");

        given()
                .param("date", "2022.01.02")
                .param("starttime", "10.00.00")
                .param("endtime", "17.00.00")
                .get("/shift/get/datetime")
                .then().statusCode(200)
                .body("size()", equalTo(2));


        String str =
                given()
                .param("date", "2022.01.02")
                .param("starttime", "10.00.00")
                .param("endtime", "17.00.00")
                .get("/shift/check/datetime")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");
    }

    @Test
    public void testCreateAndGetByEmployeeAndDateAndTimes() {
        final int id = given()
                .param("date", "2022.01.02")
                .param("starttime", "10.00.00")
                .param("endtime", "17.00.00")
                .param("status", "assigned")
                .post("/shift/create")
                .then().statusCode(200)
                .extract().response().body().path("id");


        final int employeeID = given()
                .param("firstname", "Mario")
                .param("lastname", "Bouzakhm")
                .param("email", "mariobouzakhm03@gmail.com")
                .param("password", "12345678")
                .param("empStatus", "hired")
                .post("/employee/create")
                .then().statusCode(200)
                .extract().response().body().path("id");

        given()
                .param("shiftID", id)
                .param("employeeID", employeeID)
                .post("/shift/update/employee")
                .then().statusCode(200);

        given()
                .param("id", employeeID)
                .param("date", "2022.01.02")
                .param("starttime", "10.00.00")
                .param("endtime", "17.00.00")
                .get("/shift/get/empdatetime")
                .then().statusCode(200)
                .body("[0].id", equalTo(id))
                .body("[0].employeeDto.id", equalTo(employeeID));

        String str = given()
                .param("id", employeeID)
                .param("date", "2022.01.02")
                .param("starttime", "10.00.00")
                .param("endtime", "17.00.00")
                .get("/shift/check/empdatetime")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");
    }

    @Test
    public void testCreateAndDeleteShift() {
        final int id = given()
                .param("date", "2022.01.02")
                .param("starttime", "08.30.00")
                .param("endtime", "17.00.00")
                .param("status", "assigned")
                .post("/shift/create")
                .then().statusCode(200)
                .body("date", equalTo("2022-01-02"))
                .extract().response().body().path("id");


        when().get("/shift/get/id?id=" + id).then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("date", equalTo("2022-01-02"))
                .body("startTime", equalTo("08:30:00"))
                .body("endTime", equalTo("17:00:00"))
                .body("shiftStatus", equalTo("assigned"));


        String str = given()
                .param("id", id)
                .delete("/shift/delete/")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");
    }

    @Test
    public void testCreateAndGetAllAndDeleteAll() {
        given()
                .param("date", "2022.01.02")
                .param("starttime", "10.00.00")
                .param("endtime", "17.00.00")
                .param("status", "assigned")
                .post("/shift/create");
        given()
                .param("date", "2022.01.02")
                .param("starttime", "10.00.00")
                .param("endtime", "17.00.00")
                .param("status", "assigned")
                .post("/shift/create");

        when().get("/shifts/")
                .then().statusCode(200)
                .body("size()", equalTo(2));

        String str = when().delete("/shifts/delete")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");

        when().get("/shifts/")
                .then().statusCode(200)
                .body("size()", equalTo(0));
    }


    @Test
    public void testCreateAndGetAndUpdateStatus() {
        final int id = given()
                .param("date", "2022.01.02")
                .param("starttime", "10.00.00")
                .param("endtime", "17.00.00")
                .param("status", "assigned")
                .post("/shift/create")
                .then().statusCode(200)
                .extract().response().body().path("id");

        given()
                .param("shiftID", id)
                .param("status", "available")
                .post("/shift/update/status")
                .then().statusCode(200)
                .body("shiftStatus", equalTo("available"));
    }
}
