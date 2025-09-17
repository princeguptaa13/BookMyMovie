package com.bms.BookMyMovie.Repository;

import com.bms.BookMyMovie.Entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre , Long> {
   List<Theatre> findByCity(String city);
}
