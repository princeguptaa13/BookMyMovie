package com.bms.BookMyMovie.Controller;

import com.bms.BookMyMovie.Dto.MoviesDto;
import com.bms.BookMyMovie.Service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MoviesDto> createMovies(@Valid @RequestBody MoviesDto moviesDto){
        return  new ResponseEntity<>(movieService.createMovie(moviesDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoviesDto> getMovieById(@PathVariable Long id){
        return  ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MoviesDto>> getAllMovies(){
        return  ResponseEntity.ok(movieService.getAllMovies());
    }
}
