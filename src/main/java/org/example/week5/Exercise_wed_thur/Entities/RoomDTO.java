package org.example.week5.Exercise_wed_thur.Entities;

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
public class RoomDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelDTO hotel;

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
