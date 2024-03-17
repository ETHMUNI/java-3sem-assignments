package org.example.week6.HotelAPI.Controllers;

import io.javalin.http.Handler;

public interface IController {
    Handler getAll();
    Handler getById();
    Handler create();
    Handler update();
    Handler delete();
}
