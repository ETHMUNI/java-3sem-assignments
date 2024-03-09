package org.example.week5.presentation;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {

        Map<String, String> users = new HashMap<>();

        users.put("1", "John Doe");

        Javalin app = Javalin.create().start(7007);

        app.get("/users/{id}", ctx -> {

            String userId = ctx.pathParam("id");
            ctx.sessionAttribute("id");
            ctx.attribute("id", userId);
            if (users.containsKey(userId)) {

                ctx.result("ID: " + userId + ", Name: " + users.get(userId));
            } else {
                ctx.status(404).result("User not found");
            }
        });
    }
}
