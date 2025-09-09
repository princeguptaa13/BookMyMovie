package com.bms.BookMyMovie.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "seats")
@NoArgsConstructor
@AllArgsConstructor
public class Seats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String seatNumber ;

    @Column(nullable = false)
    private String seatCategory;

    @Column(nullable = false)
    private Double basePrice;

    @ManyToOne
    @JoinColumn(name = "screen_id",nullable = false)
    private Screen screen;
}
