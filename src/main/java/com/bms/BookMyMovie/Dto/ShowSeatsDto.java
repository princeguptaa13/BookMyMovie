package com.bms.BookMyMovie.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowSeatsDto {

    private Long id;
    private SeatsDto seats;
    private String status;
    private Double price;
}
