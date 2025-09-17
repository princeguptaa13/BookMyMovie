package com.bms.BookMyMovie.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeatsDto {
    private Long seatsId;
    private String seatNumber;
    private String seatCategory;
    private Double basePrice;
}
