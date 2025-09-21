package com.bms.BookMyMovie.Service;

import com.bms.BookMyMovie.Dto.ShowsDto;
import com.bms.BookMyMovie.Entity.Movies;
import com.bms.BookMyMovie.Entity.Shows;
import com.bms.BookMyMovie.Exception.ResouceNotFoundException;
import com.bms.BookMyMovie.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowService {

    @Autowired
    private MovieRepository movieRepository;

    public ShowsDto createShow(ShowsDto showsDto){
        Shows show = new Shows();
        Movies movie = movieRepository.findById(showsDto.getMovies().getMovieId())
                .orElseThrow((()-> new ResouceNotFoundException("Movie Not Found")));
        Movies screen = movieRepository.findById(showsDto.getScreens().getScreensId())
                .orElseThrow((()-> new ResouceNotFoundException("Movie Not Found")));
    }
}
