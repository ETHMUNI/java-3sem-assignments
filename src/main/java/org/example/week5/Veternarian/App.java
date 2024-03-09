package org.example.week5.Veternarian;

import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7007);
        app.routes(getPatientRessource());
        app.routes(getAppointmentRessource());
    }


    private static EndpointGroup getPatientRessource() {
        PatientHandler patientHandler = new PatientHandler();
        return () -> {
            path("/api/vet", () -> {
                get("/patients", ctx -> patientHandler.getAllPatients(ctx));
                get("/patient/{id}", ctx -> patientHandler.getPatientDetailsById(ctx));
            });
        };
    }

    private static EndpointGroup getAppointmentRessource() {
        AppointmentHandler appointmentHandler = new AppointmentHandler();
        return () -> {
            path("/api/vet", () -> {
                get("/appointments", ctx -> appointmentHandler.getAllAppointments(ctx));
                get("/appointment/{id}", ctx -> appointmentHandler.getAppointmentById(ctx));
            });
        };
    }
}
