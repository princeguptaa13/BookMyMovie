package com.bms.BookMyMovie.Repository;

import com.bms.BookMyMovie.Entity.Shows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepository extends JpaRepository<Shows , Long> {
    List<Shows> findByMovieId(Long movieId);

    List<Shows> findByScreenId(Long screenId);

    //made SQl query of between
    List<Shows> findByStartTimeBetween(LocalDateTime start , LocalDateTime end);


}
