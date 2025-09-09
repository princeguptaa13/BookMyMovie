package com.bms.BookMyMovie.Repository;

import com.bms.BookMyMovie.Entity.ShowSeats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatsRepository extends JpaRepository<ShowSeats , Long> {

    List<ShowSeats> findByShowId(Long movieId);

    List<ShowSeats> findByShowIdAndStatus(Long showId , String status);
}
