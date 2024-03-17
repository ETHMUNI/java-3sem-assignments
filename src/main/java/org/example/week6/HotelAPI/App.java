package org.example.week6.HotelAPI;

import io.javalin.Javalin;
import org.example.week5.Exercise_wed_thur.Routes;

import java.util.Map;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7007);

        org.example.week5.Exercise_wed_thur.Routes routes = new Routes();


        app.routes(routes.getHotelRoutes());
        app.routes(routes.getRoomRoutes());


        // Handle 404 Not Found for missing resources and routes
        app.error(404, ctx -> {
            ctx.json(Map.of("message", "Resource not found"));
        });

        //for incorrect JSON representations
        app.exception(IllegalStateException.class, (e, ctx) -> {
            ctx.status(400).json(Map.of("message", e.getMessage())); // 400 Bad Request indicates a client error
        });
    }
}
