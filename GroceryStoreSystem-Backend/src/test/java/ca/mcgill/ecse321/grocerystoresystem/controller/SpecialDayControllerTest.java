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
import ca.mcgill.ecse321.grocerystoresystem.dao.CalendarRepository;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SpecialDayControllerTest {
  
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
  public void testGetNumSpecialDays() {
    
      when().get("/specialdays").then()
              .statusCode(200)
              .body("$", empty());
  }

  @Test
  public void testCreateAndQuerySpecialDayID() {
      final int id = given()
              .param("startTime", "2022.01.02/12.12.12")
              .param("endTime", "2022.01.03/12.12.12")
              .post("/specialday/create/")
              .then().statusCode(200)
              .body("startTimestamp", equalTo("2022-01-02T12:12:12"))
              .body("endTimestamp", equalTo("2022-01-03T12:12:12"))
              .extract().response().body().path("id");
       
       
      when().get("/specialday/get/id?id="+id)
              .then().statusCode(200)
              .body("id", equalTo(id))
              .body("startTimestamp", equalTo("2022-01-02T12:12:12"))
              .body("endTimestamp", equalTo("2022-01-03T12:12:12"));
       
       
      String str =  given()
              .param("id", id)
              .get("/specialday/check/id/")
              .then().statusCode(200)
              .extract().response().body().asPrettyString();

      assertEquals(str, "true");
  }
 
  @Test
  public void testCreateGetAndDeleteSpecialDay() {
    final int id = given()
        .param("startTime", "2022.01.02/12.12.12")
        .param("endTime", "2022.01.03/12.12.12")
        .post("/specialday/create/")
        .then().statusCode(200)
        .body("startTimestamp", equalTo("2022-01-02T12:12:12"))
        .body("endTimestamp", equalTo("2022-01-03T12:12:12"))
        .extract().response().body().path("id");
 
 
    when().get("/specialday/get/id?id="+id)
        .then().statusCode(200)
        .body("id", equalTo(id))
        .body("startTimestamp", equalTo("2022-01-02T12:12:12"))
        .body("endTimestamp", equalTo("2022-01-03T12:12:12"));
 
 
    String str =  given()
        .param("id", id)
        .get("/specialday/check/id/")
        .then().statusCode(200)
        .extract().response().body().asPrettyString();
   
      assertEquals(str, "true");

      String str2 = when().delete("/specialday/delete?id="+id)
              .then().statusCode(200)
              .extract().response().body().asPrettyString();

      assertEquals(str, str2);
  }

  @Test
  public void testUpdateSpecialDay() {
    final int id = given()
        .param("startTime", "2022.01.02/12.12.12")
        .param("endTime", "2022.01.03/12.12.12")
        .post("/specialday/create/")
        .then().statusCode(200)
        .body("startTimestamp", equalTo("2022-01-02T12:12:12"))
        .body("endTimestamp", equalTo("2022-01-03T12:12:12"))
        .extract().response().body().path("id");
 
 
    when().get("/specialday/get/id?id="+id)
        .then().statusCode(200)
        .body("id", equalTo(id))
        .body("startTimestamp", equalTo("2022-01-02T12:12:12"))
        .body("endTimestamp", equalTo("2022-01-03T12:12:12"));
 
 
    String str =  given()
        .param("id", id)
        .get("/specialday/check/id/")
        .then().statusCode(200)
        .extract().response().body().asPrettyString();
   
      assertEquals(str, "true");
      
    given()
        .param("id", id)
        .param("startTime", "2022.01.03/12.12.12")
        .param("endTime", "2022.01.04/12.12.12") 
        .post("/specialday/update")
        .then().statusCode(200)
        .extract().response().body().prettyPrint();
    
    when().get("/specialday/get/id?id="+id)
        .then().statusCode(200)
        .body("id", equalTo(id))
        .body("startTimestamp", equalTo("2022-01-03T12:12:12"))
        .body("endTimestamp", equalTo("2022-01-04T12:12:12"));
    
    String str2 =  given()
        .param("id", id)
        .get("/specialday/check/id/")
        .then().statusCode(200)
        .extract().response().body().asPrettyString();
   
      assertEquals(str2, "true");
  }
  
}