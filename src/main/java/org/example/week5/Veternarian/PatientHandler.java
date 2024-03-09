package org.example.week5.Veternarian;

import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class PatientHandler {

    private static final Map<String, PatientDTO> patients = new HashMap<String, PatientDTO>() {{
        put("1", new PatientDTO("1", "Fluffy", "Cat", "Persian", null, null, "No significant medical history"));
        put("2", new PatientDTO("2", "Rex", "Dog", "Labrador", null, null, "Allergic to pollen"));
        put("3", new PatientDTO("3", "Tweety", "Bird", "Canary", null, null, "None"));
    }};

    public static void getPatientDetailsById(Context ctx) {

        String patientId = ctx.pathParam("id");
        PatientDTO patient = patients.get(patientId);
        if (patient != null) {
            ctx.json(patient);
            ctx.status(200);
        } else {
            ctx.status(404);
        }
    }

    public static void getAllPatients(Context ctx) {
        ctx.json(patients.values()); // returns patientsDTO objects stored in the pateints map in a json format
        ctx.status(200);
    }
}
