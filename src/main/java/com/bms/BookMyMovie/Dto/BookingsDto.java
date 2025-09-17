package com.bms.BookMyMovie.Dto;

import com.bms.BookMyMovie.Entity.ShowSeats;
import com.bms.BookMyMovie.Entity.Users;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingsDto {
    private Long id ;
    private LocalDateTime bookingTime;
    private ShowsDto showDto;
    private String bookingNumber;
    private UsersDto users;
    private Double totalAmount;
    private String status;
    private List<ShowSeatsDto> showSeats;
    private PaymentsDto payment ;
}
