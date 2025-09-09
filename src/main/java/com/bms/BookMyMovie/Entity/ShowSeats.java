package com.bms.BookMyMovie.Entity;

import com.bms.BookMyMovie.Enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "show_seats")
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id ;

    @ManyToOne
    @JoinColumn(name = "shows_id" , nullable = false)
    private Shows shows;

    @ManyToOne
    @JoinColumn(name = "seat_id" , nullable = false)
    private Seats seats;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Bookings booking;
}
