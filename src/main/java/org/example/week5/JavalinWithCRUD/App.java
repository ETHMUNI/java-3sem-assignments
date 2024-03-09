package org.example.week5.JavalinWithCRUD;

import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7007);
        app.routes(getDogRessource());
    }


private static EndpointGroup getDogRessource() {
    DogController dogController = new DogController();
    return () -> {
        path("/api/dogs", () -> {
            get("/",dogController.getAll());
            get("/{id}",dogController.getById());
                post("/",dogController.create());
                put("/{id}",dogController.update());
                delete("/{id}",dogController.delete());

        });
    };
}
}