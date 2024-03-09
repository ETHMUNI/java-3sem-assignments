package org.example.week5.Veternarian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private String id;
    private String name;
    private String species;
    private String breed;
    private List<String> allergies;
    private List<String> medications;
    private String medicalHistory;
}
