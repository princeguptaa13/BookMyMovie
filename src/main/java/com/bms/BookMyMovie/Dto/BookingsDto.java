package com.bms.BookMyMovie.Dto;

import com.bms.BookMyMovie.Entity.Payments;
import com.bms.BookMyMovie.Entity.ShowSeats;
import com.bms.BookMyMovie.Entity.Users;

import java.time.LocalDateTime;
import java.util.List;

public class BookingDto {
    private Long id ;
    private LocalDateTime bookingTime;
    private ShowDto showDto;
    private String bookingNumber;
    private Users users;
    private Double totalAmount;
    private List<ShowSeats> showSeats;
    private PaymentDto payment ;
}
