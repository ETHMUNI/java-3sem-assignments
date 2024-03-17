package org.example.week6.HotelAPI.Controllers;


import io.javalin.http.HttpStatus;
import org.example.week5.Exercise_wed_thur.Controllers.IController;
import org.example.week5.Exercise_wed_thur.DAO.HotelDAO;
import org.example.week5.Exercise_wed_thur.Entities.HotelDTO;
import org.example.week5.Exercise_wed_thur.Entities.RoomDTO;
import org.example.week5.Exercise_wed_thur.HibernateConfig;
import io.javalin.http.Handler;

import java.util.List;

public class HotelController implements IController {

    private static HotelController instance;
    private static HotelDAO hotelDAO;

    private HotelController() {}

    public static HotelController getInstance() {
        if (instance == null) {
            instance = new HotelController();
            hotelDAO = new HotelDAO(HotelDTO.class, HibernateConfig.getEntityManagerFactory());
        }
        return instance;
    }

    public Handler getAllRoomsForHotel() {
        return ctx -> {
            int hotelId = Integer.parseInt(ctx.pathParam("id"));
            List<RoomDTO> rooms;
            try {
                rooms = hotelDAO.getAllRoomsForHotel(hotelId);
            } catch (Exception e) {
                ctx.status(500).result("Server error " + e.getMessage());
                return;
            }
            if (rooms.isEmpty()) {
                ctx.status(404).result("No rooms found for hotel with ID: " + hotelId);
            } else {
                ctx.status(HttpStatus.OK).json(rooms);
            }
        };
    }
    @Override
    public Handler getAll() {
        return ctx ->
            ctx.status(HttpStatus.OK).json(hotelDAO.getAll());
    }

    @Override
    public Handler getById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            HotelDTO hotel = hotelDAO.getById(id);
            if (hotel == null) {
                ctx.status(404).result("No hotel with that id");
            } else {
                ctx.status(HttpStatus.OK).json(hotel);
            }
        };
    }

    @Override
    public Handler create() {
        return ctx -> {
            HotelDTO hotel = ctx.bodyAsClass(HotelDTO.class);
            HotelDTO createdHotel = hotelDAO.create(hotel);
            ctx.status(HttpStatus.CREATED).json(createdHotel);
        };
    }

    @Override
    public Handler update() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            HotelDTO hotelToUpdate = ctx.bodyAsClass(HotelDTO.class);
            HotelDTO existingHotel = hotelDAO.getById(id);
            if (existingHotel == null) {
                ctx.status(404).result("No hotel with that id to update");
                return;
            }
            existingHotel.setName(hotelToUpdate.getName());
            hotelDAO.update(existingHotel);
            ctx.status(HttpStatus.OK).json(existingHotel);
        };
    }

    @Override
    public Handler delete() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            HotelDTO existingHotel = hotelDAO.getById(id);
            if (existingHotel == null) {
                ctx.status(404).result("No hotel with that id to delete");
                return;
            }
            hotelDAO.delete(id);
            ctx.status(HttpStatus.OK).json(existingHotel);
        };
    }
}