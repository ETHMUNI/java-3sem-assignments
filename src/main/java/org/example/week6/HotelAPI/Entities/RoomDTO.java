package org.example.week6.HotelAPI.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.week5.Exercise_wed_thur.Entities.HotelDTO;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private org.example.week5.Exercise_wed_thur.Entities.HotelDTO hotel;

    @Column(name = "room_number")
    private int roomNumber;
    @Column(name = "room_price")
    private int price;

    public RoomDTO(HotelDTO hotel, int roomNumber, int price) {
        this.hotel = hotel;
        this.roomNumber = roomNumber;
        this.price = price;
    }

}
