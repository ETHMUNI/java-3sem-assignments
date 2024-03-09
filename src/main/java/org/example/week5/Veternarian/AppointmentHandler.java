package org.example.week5.Veternarian;


import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class AppointmentHandler {

    private static final Map<String, AppointmentDTO> appointments = new HashMap<String, AppointmentDTO>(){{
        put("1", new AppointmentDTO("1", "1", "2024-03-03 Time: 10:00", "Annual checkup"));
        put("2", new AppointmentDTO("2", "2", "2024-03-03 Time: 11:00", "Vaccination"));
        put("3", new AppointmentDTO("3", "3", "2024-03-03 Time: 12:00", "Beak trimming"));
    }};
    public static void getAllAppointments(Context ctx) {
        String appointmentId = ctx.pathParam("id");
        AppointmentDTO appointment = appointments.get(appointmentId);
        if (appointment != null) {
            ctx.json(appointment);
            ctx.status(200);
        } else {
            ctx.status(404);
        }
    }

    public static void getAppointmentById(Context ctx) {
        ctx.json(appointments.values()); // returns appointmentDTO objects stored in the appointment map in a json format
        ctx.status(200);
    }
}

