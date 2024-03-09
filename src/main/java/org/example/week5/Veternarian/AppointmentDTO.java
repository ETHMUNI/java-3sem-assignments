package org.example.week5.Veternarian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private String id;
    private String patientId;
    private String dateTime;
    private String description;
}

