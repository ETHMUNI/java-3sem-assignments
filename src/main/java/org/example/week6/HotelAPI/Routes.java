package org.example.week6.HotelAPI;

import io.javalin.apibuilder.EndpointGroup;
import org.example.week5.Exercise_wed_thur.Controllers.HotelController;
import org.example.week5.Exercise_wed_thur.Controllers.RoomController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {

    private HotelController hotelController = HotelController.getInstance();
    private RoomController roomController = RoomController.getInstance();

    public EndpointGroup getHotelRoutes() {
        return () -> {
            path("/hotel", () -> {
                get("/", hotelController.getAll());
                get("/{id}", hotelController.getById());
                post("/", hotelController.create());
                put("/{id}", hotelController.update());
                delete("/{id}", hotelController.delete());
                path("/{id}/rooms", () -> {
                    get("/", hotelController.getAllRoomsForHotel());
                });
            });
        };
    }

    public EndpointGroup getRoomRoutes() {
        return () -> {
            path("/room", () -> {
                get("/", roomController.getAll());
                get("/{id}", roomController.getById());
                post("/", roomController.create());
                put("/{id}", roomController.update());
                delete("/{id}", roomController.delete());
            });
        };
    }
}
