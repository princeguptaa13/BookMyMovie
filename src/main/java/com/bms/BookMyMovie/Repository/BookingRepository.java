package com.bms.BookMyMovie.Repository;

import com.bms.BookMyMovie.Entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Bookings ,Long> {

    List<Bookings> findByUserid(Long userId);

    Optional<Bookings> findByBookingNumber(String bookingNumber);

    List<Bookings> findByShowId(Long id);
}
