package com.bms.BookMyMovie.Repository;

import com.bms.BookMyMovie.Entity.Shows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Shows , Long> {
    List<Shows> findByMovieId(Long movieId);

    List<Shows> findByScreenId(Long screenId);

    //made SQl query of between
    List<Shows> findByStartTimeBetween(LocalDateTime start , LocalDateTime end);

    List<Shows> findByMovie_IdAndScreen_Theatre_City(Long movieId , String city);


}
