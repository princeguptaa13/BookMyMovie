package com.bms.BookMyMovie.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "shows")
@NoArgsConstructor
@AllArgsConstructor
public class Shows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<ShowSeats> showSeats;


    @ManyToOne
    @JoinColumn(name = "movie_id" , nullable = false)
    private Movies movie;

    @ManyToOne
    @JoinColumn(name = "screen_id" , nullable = false)
    private Screen screen ;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL) // must match field in Bookings
    private List<Bookings> bookingsList;


}
