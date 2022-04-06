package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dao.AddressRepository;
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
public class OwnerControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        this.personRepository.deleteAll();

        this.addressRepository.deleteAll();
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
    public void testCreateAndQueryOwnerID() {
        final int id = given()
                .param("firstname", "Mario")
                .param("lastname", "Bouzakhm")
                .param("email", "mariobouzakhm02@gmail.com")
                .param("password", "12345678")
                .post("/owner/create")
                .then().statusCode(200)
                .body("firstName", equalTo("Mario"))
                .body("lastName", equalTo("Bouzakhm"))
                .body("email", equalTo("mariobouzakhm02@gmail.com"))
                .extract().response().body().path("id");

        when().get("/owner/get/id?id="+id)
                .then().statusCode(200)
                .body("id", equalTo(id));

        String str =  given()
                .param("id", id)
                .get("/owner/check/id/")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");
    }

    @Test
    public void testCreateAndQueryOwnerFirstName() {
        when().delete("/owners/delete").then().statusCode(200);

        given()
                .param("firstname", "Mario")
                .param("lastname", "Bouzakhm")
                .param("email", "mariobouzakhm02@gmail.com")
                .param("password", "12345678")
                .post("/owner/create");

        given()
                .param("firstname", "Mario")
                .param("lastname", "Bouzakhm2")
                .param("email", "mariobouzakhm03@gmail.com")
                .param("password", "12345678")
                .post("/owner/create");

        given()
                .param("firstname", "Mario")
                .get("/owner/get/firstname/")
                .then().statusCode(200)
                .body("size()", equalTo(2));

        String str =  given()
                .param("firstname", "Mario")
                .get("/owner/check/firstname/")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");
    }

    @Test
    public void testCreateAndQueryOwnerLastName() {

        when().delete("/owners/delete").then().statusCode(200);

        given()
                .param("firstname", "Mario")
                .param("lastname", "Bouzakhm")
                .param("email", "mariobouzakhm02@gmail.com")
                .param("password", "12345678")
                .post("/owner/create");

        given()
                .param("firstname", "Mario")
                .param("lastname", "Bouzakhm")
                .param("email", "mariobouzakhm03@gmail.com")
                .param("password", "12345678")
                .post("/owner/create");

        given()
                .param("lastname", "Bouzakhm")
                .get("/owner/get/lastname/")
                .then().statusCode(200)
                .body("size()", equalTo(2));

        String str =  given()
                .param("lastname", "Bouzakhm")
                .get("/owner/check/lastname/")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");
    }

    @Test
    public void testCreateAndQueryOwnerFullName() {
        when().delete("/owners/delete").then().statusCode(200);

        given()
                .param("firstname", "Mario")
                .param("lastname", "Bouzakhm")
                .param("email", "mariobouzakhm02@gmail.com")
                .param("password", "12345678")
                .post("/owner/create");

        given()
                .param("firstname", "Mario")
                .param("lastname", "Bouzakhm")
                .param("email", "mariobouzakhm03@gmail.com")
                .param("password", "12345678")
                .post("/owner/create");

        given()
                .param("firstname", "Mario")
                .param("lastname", "Bouzakhm")
                .get("/owner/get/fullname/")
                .then().statusCode(200)
                .body("size()", equalTo(2));

        String str =  given()
                .param("firstname", "Mario")
                .param("lastname", "Bouzakhm")
                .get("/owner/check/fullname/")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");
    }

    @Test
    public void testCreateAndQueryOwnerEmail() {
        final int id = given()
                .param("firstname", "Mario")
                .param("lastname", "Bouzakhm")
                .param("email", "mariobouzakhm02@gmail.com")
                .param("password", "12345678")
                .post("/owner/create").then()
                .statusCode(200)
                .extract().response().body().path("id");

        given()
                .param("email", "mariobouzakhm02@gmail.com")
                .get("/owner/get/email/")
                .then().statusCode(200)
                .body("firstName", equalTo("Mario"))
                .body("lastName", equalTo("Bouzakhm"))
                .body("id", equalTo(id));

        String str =  given()
                .param("email", "mariobouzakhm02@gmail.com")
                .get("/owner/check/email/")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");
    }

    @Test
    public void testCreateGetAndDeleteOwner() {
        final int id = given()
                .param("firstname", "Mario")
                .param("lastname", "Bouzakhm")
                .param("email", "mariobouzakhm02@gmail.com")
                .param("password", "12345678")
                .post("/owner/create").then()
                .statusCode(200)
                .extract().response().body().path("id");

        when().get("/owner/get/id?id="+id)
                .then().statusCode(200)
                .body("id", equalTo(id));

        String str =  given()
                .param("id", id)
                .get("/owner/check/id/")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");

        String str2 = when().delete("/owner/delete?id="+id)
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, str2);
    }

    @Test
    public void testCreateAndDeleteMultipleOwners() {

        when().delete("/owners/delete").then().statusCode(200);

        given()
                .param("firstname", "Mario")
                .param("lastname", "Bouzakhm")
                .param("email", "mariobouzakhm02@gmail.com")
                .param("password", "12345678")
                .post("/owner/create").then()
                .statusCode(200);

        given()
                .param("firstname", "Mario")
                .param("lastname", "Bouzakhm")
                .param("email", "mariobouzakhm03@gmail.com")
                .param("password", "12345678")
                .post("/owner/create").then()
                .statusCode(200);

        given()
                .param("firstname", "Mario")
                .param("lastname", "Bouzakhm")
                .param("email", "mariobouzakhm04@gmail.com")
                .param("password", "12345678")
                .post("/owner/create").then()
                .statusCode(200);

        when().get("/owners")
                .then().statusCode(200)
                .body("size()", equalTo(3));

        String str = when().delete("/owners/delete/")
                .then().statusCode(200)
                .extract().response().body().asPrettyString();

        assertEquals(str, "true");

        when().get("/owners")
                .then().statusCode(200)
                .body("size()", equalTo(0));

    }

    @Test
    public void testCreateAndUpdateAddress() {
        final int id = given()
                .param("firstname", "Mario")
                .param("lastname", "Bouzakhm")
                .param("email", "mariobouzakhm02@gmail.com")
                .param("password", "12345678")
                .post("/owner/create").then()
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
                .post("/owner/update/address/")
                .then().statusCode(200)
                .extract().response().body().prettyPrint();
    }
}
