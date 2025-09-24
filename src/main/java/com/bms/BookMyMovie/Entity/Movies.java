package com.bms.BookMyMovie.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false)
    private String title;

    private String description ;

    private String genre ;

    private String language;

    private Integer duration ;

    private String releaseDate ;

    private String posterUrl;

    @OneToMany(mappedBy = "movie" ,cascade = CascadeType.ALL)
    private List<Shows> show;
}
