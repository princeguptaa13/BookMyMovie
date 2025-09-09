package com.bms.BookMyMovie.Repository;

import com.bms.BookMyMovie.Entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre , Long> {
   List<Theatre> findByCity(String city);
}
