package com.bms.BookMyMovie.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "screens")
@NoArgsConstructor
@AllArgsConstructor
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer totalSeats ;

    @ManyToOne
    @JoinColumn(name = "theatre_id" , nullable = false)
    private Theatre theatre ;

    @OneToMany(mappedBy = "screen",cascade = CascadeType.ALL)
    private List<Shows> shows;

    @OneToMany(mappedBy = "screen",cascade = CascadeType.ALL)
    private List<Seats> seats;
}
