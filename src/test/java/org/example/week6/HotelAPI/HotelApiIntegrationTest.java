package org.example.week6.HotelAPI;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

class HotelApiIntegrationTest {


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 7007;
    }

    @Test
    public void testGetAllHotels() {
        given()
                .when()
                .get("/hotel/")
                .then()
                .statusCode(200)
                .body("", hasSize(greaterThan(0)));
    }

    @Test
    public void testGetHotelById() {
        int hotelId = 1;
        given()
                .pathParam("id", hotelId)
                .when()
                .get("/hotel/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(hotelId));
    }

    @Test
    public void testCreateHotel() {
        String newHotel = "{\"name\":\"Test Hotel\",\"location\":\"Test City\"}";

        given()
                .contentType("application/json")
                .body(newHotel)
                .when()
                .post("/hotel/")
                .then()
                .statusCode(201)
                .body("name", equalTo("Test Hotel"));
    }

    @Test
    public void testUpdateHotel() {
        int hotelId = 1;
        String updatedHotel = "{\"name\":\"Updated Hotel\",\"location\":\"Updated City\"}";

        given()
                .contentType("application/json")
                .body(updatedHotel)
                .pathParam("id", hotelId)
                .when()
                .put("/hotel/{id}")
                .then()
                .statusCode(200)
                .body("name", equalTo("Updated Hotel"));
    }

    @Test
    public void testDeleteHotel() {
        int hotelId = 1;

        given()
                .pathParam("id", hotelId)
                .when()
                .delete("/hotel/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetAllRoomsForHotel() {
        int hotelId = 1;

        given()
                .pathParam("id", hotelId)
                .when()
                .get("/hotel/{id}/rooms")
                .then()
                .statusCode(200)
                .body("", hasSize(greaterThan(0)));
    }
}

