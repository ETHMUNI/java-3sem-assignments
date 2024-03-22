package org.example.week6and7.HotelAPI;

import io.javalin.apibuilder.EndpointGroup;
import io.javalin.security.RouteRole;
import org.example.week6and7.HotelAPI.Controllers.HotelController;
import org.example.week6and7.HotelAPI.Controllers.RoomController;
import org.example.week6and7.HotelAPI.Controllers.SecurityController;

import static io.javalin.apibuilder.ApiBuilder.*;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Routes {

    private HotelController hotelController = HotelController.getInstance();
    private RoomController roomController = RoomController.getInstance();

    private SecurityController securityController = new SecurityController();
    private static final ObjectMapper jsonMapper = new ObjectMapper();


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


    public EndpointGroup getSecurityRoutes() {
        return ()->{
            path("/auth", ()->{
                post("/login", securityController.login(),Role.ANYONE);
                post("/register", securityController.register(),Role.ANYONE);
            });
        };
    }

    public EndpointGroup getSecuredRoutes(){
        return ()->{
            path("/protected", ()->{
                before(securityController.authenticate());
                get("/user_demo",(ctx)->ctx.json(jsonMapper.createObjectNode().put("msg",  "Hello from USER Protected")),Role.USER);
                get("/admin_demo",(ctx)->ctx.json(jsonMapper.createObjectNode().put("msg",  "Hello from ADMIN Protected")),Role.ADMIN);
            });
        };
    }
    public enum Role implements RouteRole { ANYONE, USER, ADMIN }
}
