package org.example.week5.Exercise_wed_thur.Controllers;

import io.javalin.http.Handler;

public interface IController {
    Handler getAll();
    Handler getById();
    Handler create();
    Handler update();
    Handler delete();
}
