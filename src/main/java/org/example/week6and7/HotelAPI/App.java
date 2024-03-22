package org.example.week6and7.HotelAPI;

import io.javalin.Javalin;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7007);

        Routes routes = new Routes();

        app.routes(routes.getHotelRoutes());
        app.routes(routes.getRoomRoutes());
        app.routes(routes.getSecurityRoutes());
        app.routes(routes.getSecuredRoutes());


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
