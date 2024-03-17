package org.example.week6.HotelAPI.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "hotel_name")
    private String name;
    @Column(name = "hotel_address")
    private String address;
    @Column(name = "hotel_rooms")
    private int rooms;

    public HotelDTO(String name, String address, int rooms) {
        this.name = name;
        this.address = address;
        this.rooms = rooms;
    }
}
