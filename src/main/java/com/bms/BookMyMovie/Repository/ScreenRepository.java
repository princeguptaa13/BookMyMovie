package com.bms.BookMyMovie.Repository;

import com.bms.BookMyMovie.Entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepository extends JpaRepository<Screen , Long> {
    List<Screen> findByTheatreId(Long theatreId);
}
