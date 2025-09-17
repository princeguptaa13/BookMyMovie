package com.bms.BookMyMovie.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingRequestDto {
    private Long userId;
    private Long showId;
    private List<Long> seatIds;
    private String paymentMethod;
}
