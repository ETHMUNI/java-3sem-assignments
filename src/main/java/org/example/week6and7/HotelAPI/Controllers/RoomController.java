package org.example.week6and7.HotelAPI.Controllers;


import io.javalin.http.HttpStatus;
import org.example.week6and7.HotelAPI.Config.HibernateConfig;
import org.example.week6and7.HotelAPI.DAO.RoomDAO;
import io.javalin.http.Handler;
import org.example.week6and7.HotelAPI.Entities.RoomDTO;

public class RoomController implements IController {

    private static RoomController instance;
    private static RoomDAO roomDAO;

    private RoomController() {}

    public static RoomController getInstance() {
        if (instance == null) {
            instance = new RoomController();
            roomDAO = new RoomDAO(RoomDTO.class, HibernateConfig.getEntityManagerFactory());
        }
        return instance;
    }
    @Override
    public Handler getAll() {
        return ctx ->
            ctx.status(HttpStatus.OK).json(roomDAO.getAll());
    }

    @Override
    public Handler getById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            RoomDTO room = roomDAO.getById(id);
            if (room == null) {
                ctx.status(404).result("No room with that id");
            } else {
                ctx.status(HttpStatus.OK).json(room);
            }
        };
    }

    @Override
    public Handler create() {
        return ctx -> {
            RoomDTO room = ctx.bodyAsClass(RoomDTO.class);
            RoomDTO createdRoom = roomDAO.create(room);
            ctx.status(HttpStatus.CREATED).json(createdRoom);
        };
    }

    @Override
    public Handler update() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            RoomDTO roomToUpdate = ctx.bodyAsClass(RoomDTO.class);
            RoomDTO existingRoom = roomDAO.getById(id);
            if (existingRoom == null) {
                ctx.status(404).result("No room with that id");
            } else {
                roomToUpdate.setId(id);
                roomDAO.update(roomToUpdate);
                ctx.status(HttpStatus.NO_CONTENT).result("Room updated");
            }
        };
    }

    @Override
    public Handler delete() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            RoomDTO room = roomDAO.getById(id);
            if (room == null) {
                ctx.status(404).result("No room with that id");
            } else {
                roomDAO.delete(id);
                ctx.status(HttpStatus.NO_CONTENT).result("Room deleted");
            }
        };
    }
}
