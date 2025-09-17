package com.bms.BookMyMovie.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "theatres")
@NoArgsConstructor
@AllArgsConstructor
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name ;

    private String address ;

    private String city ;

    private Integer totalScreens;

    @OneToMany(mappedBy = "theatre" , cascade = CascadeType.ALL)
    private List<Screen> screens;
}
