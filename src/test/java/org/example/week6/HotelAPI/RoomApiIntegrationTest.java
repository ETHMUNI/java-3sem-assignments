package org.example.week6.HotelAPI;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

class RoomApiIntegrationTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 7007;
    }

    @Test
    public void testGetAllRooms() {
        given()
                .when()
                .get("/room/")
                .then()
                .statusCode(200)
                .body("", hasSize(greaterThan(0))) // Asserting that the list is not empty
                .body("[0].id", greaterThan(0)); // Assuming the first room has an ID greater than 0
    }

    @Test
    public void testGetRoomById() {
        int roomId = 1; // Replace with a valid room ID known to exist

        given()
                .pathParam("id", roomId)
                .when()
                .get("/room/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(roomId))
                .body("number", notNullValue()) // Assuming the room has a 'number'
                .body("type", notNullValue()); // Assuming the room has a 'type'
    }



    @Test
    public void testCreateRoom() {
        String newRoom = "{\"number\":\"101\",\"type\":\"Single\",\"hotelId\":1}";

        given()
                .contentType("application/json")
                .body(newRoom)
                .when()
                .post("/room/")
                .then()
                .statusCode(201)
                .body("number", equalTo("101"));
    }

    @Test
    public void testUpdateRoom() {
        int roomId = 1;
        String updatedRoom = "{\"number\":\"102\",\"type\":\"Double\",\"hotelId\":1}";

        given()
                .contentType("application/json")
                .body(updatedRoom)
                .pathParam("id", roomId)
                .when()
                .put("/room/{id}")
                .then()
                .statusCode(200)
                .body("number", equalTo("102"));
    }

    @Test
    public void testDeleteRoom() {
        int roomId = 1;

        given()
                .pathParam("id", roomId)
                .when()
                .delete("/room/{id}")
                .then()
                .statusCode(200);
    }






}