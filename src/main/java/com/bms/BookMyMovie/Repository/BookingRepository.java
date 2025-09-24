package com.bms.BookMyMovie.Repository;

import com.bms.BookMyMovie.Entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Bookings ,Long> {

    List<Bookings> findByUsers_Id(Long userId);

    Optional<Bookings> findByBookingNumber(String bookingNumber);

    List<Bookings> findByShow_Id(Long id);
}
