package com.bms.BookMyMovie.Controller;

import com.bms.BookMyMovie.Dto.BookingRequestDto;
import com.bms.BookMyMovie.Dto.BookingsDto;
import com.bms.BookMyMovie.Service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/bookings")
public class BookingController {

    BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingsDto> createBooking(@Valid @RequestParam BookingRequestDto bookingRequestDto){
        return new ResponseEntity<>(bookingService.createBooking(bookingRequestDto) , HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingsDto> getBookingById(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

}
