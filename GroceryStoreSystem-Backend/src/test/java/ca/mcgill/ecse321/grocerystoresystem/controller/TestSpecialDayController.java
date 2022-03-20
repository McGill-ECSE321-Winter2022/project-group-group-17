//package ca.mcgill.ecse321.grocerystoresystem.controller;
//
//import io.restassured.module.mockmvc.RestAssuredMockMvc;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.WebApplicationContext;
//import ca.mcgill.ecse321.grocerystoresystem.dao.CalendarRepository;
//import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
//import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
//import static org.hamcrest.Matchers.equalTo;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import java.time.LocalDateTime;
//
//@Tag("integration")
//@SpringBootTest
//@AutoConfigureMockMvc
//@Transactional
//public class TestSpecialDayController {
//  
//  @Autowired
//  private WebApplicationContext webApplicationContext;
//  
//  @Autowired
//  private CalendarRepository calendarRepository;
//
//  @BeforeEach
//  public void setup() {
//      RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
//
//      this.calendarRepository.deleteAll();
//  }
//  
//  @AfterEach
//  public void cleanup() {
//      RestAssuredMockMvc.reset();
//  }
//  
//  @Test
//  public void testCreateAndQuerySpecialDayID() {
//      final int id = given()
//              .post("/specialday/id")
//              .then().statusCode(200)
//              .extract().response().body().path("id");
//
//      when().get("/specialday/{id}?{id}="+id)
//              .then().statusCode(200)
//              .body("{id}", equalTo(id));
//
//      String str =  given()
//              .param("id", id)
//              .get("/specialday/check/id/")
//              .then().statusCode(200)
//              .extract().response().body().asPrettyString();
//
//      assertEquals(str, "true");
//  }
//  
//  @Test
//  public void testCreateGetAndDeleteSpecialDay() {
//      final int id = given()
//              .param("specialDayID", "1001")
//              .param("startTime", LocalDateTime.parse("2022-03-13T15:14:21.629"))
//              .param("endTime", LocalDateTime.parse("2022-03-14T15:14:21.629"))
//              .post("/specialday/create").then()
//              .statusCode(200)
//              .extract().response().body().path("id");
//
//      when().get("/specialday/id?id="+id)
//              .then().statusCode(200)
//              .body("{id}", equalTo(id));
//
//      String str =  given()
//              .param("id", id)
//              .get("/specialday/check/id/")
//              .then().statusCode(200)
//              .extract().response().body().asPrettyString();
//
//      assertEquals(str, "true");
//
//      String str2 = when().delete("/specialday/delete?id="+id)
//              .then().statusCode(200)
//              .extract().response().body().asPrettyString();
//
//      assertEquals(str, str2);
//  }
//  
//  @Test
//  public void testUpdateSpecialDay() {
//    final int id = given()
//        .param("specialID", "1001")
//        .param("startTime", LocalDateTime.parse("2022-03-14T15:14:21.629"))
//        .param("endTime", LocalDateTime.parse("2022-03-15T15:14:21.629"))
//        .post("/specialday/create").then()
//        .statusCode(200)
//        .extract().response().body().path("id");
//    
//        given()
//        .param("id", id)
//        .post("/specialday/update/{id}")
//        .then().statusCode(200)
//        .extract().response().body().prettyPrint();
//  }
//
//
//  
//}
