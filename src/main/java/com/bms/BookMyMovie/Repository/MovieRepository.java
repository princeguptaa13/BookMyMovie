package com.bms.BookMyMovie.Repository;

import com.bms.BookMyMovie.Entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movies , Long> {
    List<Movies> findByLanguage(String language);

    List<Movies> findByGenre(String genre);

    List<Movies> findByTitle(String title);
}
