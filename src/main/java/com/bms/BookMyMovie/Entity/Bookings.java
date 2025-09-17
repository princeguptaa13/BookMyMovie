package com.bms.BookMyMovie.Entity;

import com.bms.BookMyMovie.Enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false , unique = true)
    private String bookingNumber;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @ManyToMany
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @ManyToMany
    @JoinColumn(name = "show_id", nullable = false)
    private Shows show;

    @Column(nullable = false)
    private String status ; //CONFIRMED , CANCEL , PENDING

    @Column(nullable = false)
    private Double totalAmount ;

    @OneToMany(mappedBy = "booking_id" , cascade = CascadeType.ALL)
    private List<ShowSeats> showSeats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payments payment;

}
