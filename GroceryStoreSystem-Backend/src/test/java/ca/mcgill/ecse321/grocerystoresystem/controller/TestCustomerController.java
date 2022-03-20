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
public class TestCustomerController {
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
  public void testGetNoCustomers() {
      when().get("/customers").then()
              .statusCode(200)
              .body("$", empty());
  }
  
  @Test
  public void testCreateAndQueryCustomerID() {
      final int id = given()
              .param("firstName", "Yash")
              .param("lastname", "Khapre")
              .param("email", "yash.khapre@gmail.com")
              .param("password", "ya$hpas$word!")
              .post("/customer/create")
              .then().statusCode(200)
              .body("firstName", equalTo("Yash"))
              .body("lastName", equalTo("Khapre"))
              .body("email", equalTo("yash.khapre@gmail.com"))
              .extract().response().body().path("id");

      when().get("/customer//customer/{id}?{id}="+id)
              .then().statusCode(200)
              .body("{id}", equalTo(id));

      String str =  given()
              .param("id", id)
              .get("/customer/check/id/")
              .then().statusCode(200)
              .extract().response().body().asPrettyString();

      assertEquals(str, "true");
  }
  
  @Test
  public void testCreateAndQueryCustomerFirstName() {
      when().delete("/custmers/delete").then().statusCode(200);

      given()
              .param("personID", "1001")        
              .param("firstname", "Yash")
              .param("lastname", "Khapre")
              .param("email", "yash.khapre@gmail.com")
              .param("password", "ya$hpas$word!")
              .post("/customer/create");

      given()                
              .param("personID", "1002")   
              .param("firstname", "Yash")
              .param("lastname", "Khapre2")
              .param("email", "yashkhapre2@gmail.com")
              .param("password", "12345678")
              .post("/owner/create");

      given()
              .param("firstname", "Yash")
              .get("/customer/firstname/")
              .then().statusCode(200)
              .body("size()", equalTo(2));

      String str =  given()
              .param("firstname", "Yash")
              .get("/customer/check/firstname/")
              .then().statusCode(200)
              .extract().response().body().asPrettyString();

      assertEquals(str, "true");
  }
  
  @Test
  public void testCreateAndQueryCustomerLastName() {
      when().delete("/custmers/delete").then().statusCode(200);

      given()
              .param("personID", "1001")        
              .param("firstname", "Yash")
              .param("lastname", "Khapre")
              .param("email", "yash.khapre@gmail.com")
              .param("password", "ya$hpas$word!")
              .post("/customer/create");

      given()                
              .param("personID", "1002")   
              .param("firstname", "Yash")
              .param("lastname", "Khapre")
              .param("email", "yashkhapre2@gmail.com")
              .param("password", "12345678")
              .post("/owner/create");

      given()
              .param("lastname", "Khapre")
              .get("/customer/lastname/")
              .then().statusCode(200)
              .body("size()", equalTo(2));

      String str =  given()
              .param("lastname", "Khapre")
              .get("/customer/check/lastname/")
              .then().statusCode(200)
              .extract().response().body().asPrettyString();

      assertEquals(str, "true");
  }
  
  @Test
  public void testCreateAndQueryCustomerFullName() {
      when().delete("/customers/delete").then().statusCode(200);

      given()
              .param("personID", "1001")        
              .param("firstname", "Yash")
              .param("lastname", "Khapre")
              .param("email", "yash.khapre@gmail.com")
              .param("password", "ya$hpas$word!")
              .post("/customer/create");

      given()
              .param("personID", "1002")   
              .param("firstname", "Yash")
              .param("lastname", "Khapre")
              .param("email", "yashkhapre2@gmail.com")
              .param("password", "12345678")
              .post("/owner/create");

      given()
              .param("firstname", "Yash")
              .param("lastname", "Khapre")
              .get("/owner/get/fullname/")
              .then().statusCode(200)
              .body("size()", equalTo(2));

      String str =  given()
              .param("firstname", "Yash")
              .param("lastname", "Khapre")
              .get("/owner/check/fullname/")
              .then().statusCode(200)
              .extract().response().body().asPrettyString();

      assertEquals(str, "true");
  }

  @Test
  public void testCreateAndQueryCustomerEmail() {
      final int id = given()
              .param("personID", "1002")   
              .param("firstname", "Yash")
              .param("lastname", "Khapre")
              .param("email", "yashkhapre2@gmail.com")
              .param("password", "12345678")
              .post("/customer/create").then()
              .statusCode(200)
              .extract().response().body().path("id");

      given()
              .param("email", "yashkhapre2@gmail.com")
              .get("/customer/email/")
              .then().statusCode(200)
              .body("firstName", equalTo("Yash"))
              .body("lastName", equalTo("Khapre"))
              .body("id", equalTo(id));

      String str =  given()
              .param("email", "yashkhapre2@gmail.com")
              .get("/customer/check/email/")
              .then().statusCode(200)
              .extract().response().body().asPrettyString();

      assertEquals(str, "true");
  }

  @Test
  public void testCreateGetAndDeleteCustomer() {
      final int id = given()
              .param("personID", "1001")
              .param("firstname", "Yash")
              .param("lastname", "Khapre")
              .param("email", "yash.khapre@gmail.com")
              .param("password", "12345678")
              .post("/customer/create").then()
              .statusCode(200)
              .extract().response().body().path("id");

      when().get("/customer/{id}?{id}="+id)
              .then().statusCode(200)
              .body("{id}", equalTo(id));

      String str =  given()
              .param("id", id)
              .get("/customer/check/id/")
              .then().statusCode(200)
              .extract().response().body().asPrettyString();

      assertEquals(str, "true");

      String str2 = when().delete("/customer/delete?{id}="+id)
              .then().statusCode(200)
              .extract().response().body().asPrettyString();

      assertEquals(str, str2);
  }

  @Test
  public void testCreateAndDeleteMultipleCustomers() {

      when().delete("/customers/delete").then().statusCode(200);

      given()
              .param("personID", "1001")
              .param("firstname", "Yash")
              .param("lastname", "Khapre")
              .param("email", "yash.khapre@gmail.com")
              .param("password", "12345678")
              .post("/customer/create").then()
              .statusCode(200);

      given()
              .param("personID", "1002")
              .param("firstname", "Yash")
              .param("lastname", "Khapre")
              .param("email", "yash.khapre2@gmail.com")
              .param("password", "12345678")
              .post("/customer/create").then()
              .statusCode(200);

      given()
              .param("personID", "1003")
              .param("firstname", "Yash")
              .param("lastname", "Khapre")
              .param("email", "yash.khapre4@gmail.com")
              .param("password", "12345678")
              .post("/customer/create").then()
              .statusCode(200);

      when().get("/customers")
              .then().statusCode(200)
              .body("size()", equalTo(3));

      String str = when().delete("/customers/delete/")
              .then().statusCode(200)
              .extract().response().body().asPrettyString();

      assertEquals(str, "true");

      when().get("/customers")
              .then().statusCode(200)
              .body("size()", equalTo(0));
  }
  
  @Test
  public void testCreateAndUpdateAddress() {
      final int id = given()
              .param("firstname", "Yash")
              .param("lastname", "Khapre")
              .param("email", "yash.khapre@gmail.com")
              .param("password", "ya$hpa$sword")
              .post("/customer/create").then()
              .statusCode(200)
              .extract().response().body().path("id");

      final int addressID = given()
              .param("streetName", "Hutchison")
              .param("streetNum", "3445")
              .param("city", "Montreal")
              .param("postalCode", "H2X 2G1")
              .param("country", "Canada")
              .param("isLocal", true)
              .post("/address/create/")
              .then().statusCode(200)
              .extract().response().body().path("id");

      given()
              .param("id", id)
              .param("addressID", addressID)
              .post("/customer/update/address/")
              .then().statusCode(200)
              .extract().response().body().prettyPrint();
  }
  
  @Test
  public void testCreateAndQueryCustomerLocal() {
    
    when().get("/customers/local").then().statusCode(200).body("size()", equalTo(1));
    
    given()
            .param("personID", "1001")
            .param("firstname", "Yash")
            .param("lastname", "Khapre")
            .param("email", "yash.khapre@gmail.com")
            .param("password", "12345678")
            .post("/customer/create").then()
            .statusCode(200);
    given()
           .param("streetName", "Hutchison")
           .param("streetNum", "3445")
           .param("city", "Montreal")
           .param("postalCode", "H2X 2G1")
           .param("country", "Canada")
           .param("isLocal", true)
           .post("/address/create/")
           .then().statusCode(200);
  }
  
  @Test
  public void testCreateAndQueryCustomerNonLocal() {
    
    when().get("/customers/local").then().statusCode(200).body("size()", equalTo(1));
    
    given()
            .param("personID", "1001")
            .param("firstname", "Yash")
            .param("lastname", "Khapre")
            .param("email", "yash.khapre@gmail.com")
            .param("password", "12345678")
            .post("/customer/create").then()
            .statusCode(200);
    given()
           .param("streetName", "Hutchison")
           .param("streetNum", "3445")
           .param("city", "Montreal")
           .param("postalCode", "H2X 2G1")
           .param("country", "Canada")
           .param("isLocal", false)
           .post("/address/create/")
           .then().statusCode(200);
  }
  
}
