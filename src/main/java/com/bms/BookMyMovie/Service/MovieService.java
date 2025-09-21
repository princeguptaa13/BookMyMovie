package com.bms.BookMyMovie.Service;

import com.bms.BookMyMovie.Dto.MoviesDto;
import com.bms.BookMyMovie.Entity.Movies;
import com.bms.BookMyMovie.Exception.ResouceNotFoundException;
import com.bms.BookMyMovie.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MoviesDto createMovie(MoviesDto moviesDto){
             Movies movies = mapToEntity(moviesDto);
             Movies saveMovies = movieRepository.save(movies);
             return mapToDto(saveMovies);
    }

    public MoviesDto getMovieById(Long id){
        Movies movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Movie Not Found With ID = "+id));
        return mapToDto(movie);
    }

    public List<MoviesDto> getMovieByLanguage(String language){
        List<Movies> movies = movieRepository.findByLanguage(language);
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

    }
    public List<MoviesDto> getMovieByTitle(String title){
        List<Movies> movies = movieRepository.findByTitle(title);
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

    }
    public List<MoviesDto> getMovieByGenre(String genre){
        List<Movies> movies = movieRepository.findByGenre(genre);
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public MoviesDto updateMovie(Long id , MoviesDto moviesDto){
        Movies movies = movieRepository.findById(id)
                .orElseThrow((()-> new ResouceNotFoundException("Movie Not Found with Id = "+id)));
        movies.setTitle(moviesDto.getTitle());
        movies.setDescription(moviesDto.getDescription());
        movies.setLanguage(moviesDto.getLanguage());
        movies.setGenre(moviesDto.getGenre());
        movies.setReleaseDate(moviesDto.getReleaseDate());
        movies.setDuration(moviesDto.getDurationMinutes());
        movies.setPosterUrl(moviesDto.getPosterUrl());

        Movies updated = movieRepository.save(movies);
        return mapToDto(updated);
    }

    public void deleteMovie(Long id){
     Movies movies = movieRepository.findById(id)
             .orElseThrow((()-> new ResouceNotFoundException("Movie Not Found with Id = "+id)));
     movieRepository.delete(movies);
    }

    public List<MoviesDto> getAllMovies(){
        List<Movies> movies = movieRepository.findAll();
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private MoviesDto mapToDto(Movies movies){
        MoviesDto moviesDto = new MoviesDto();
        moviesDto.setMovieId(movies.getId());
        moviesDto.setTitle(movies.getTitle());
        moviesDto.setDescription(movies.getDescription());
        moviesDto.setLanguage(movies.getLanguage());
        moviesDto.setGenre(movies.getGenre());
        moviesDto.setPosterUrl(moviesDto.getPosterUrl());
        moviesDto.setReleaseDate(movies.getReleaseDate());
        moviesDto.setPosterUrl(movies.getPosterUrl());

        return moviesDto;
    }

    public Movies mapToEntity(MoviesDto moviesDto){
        Movies movies = new Movies();
        movies.setId(moviesDto.getMovieId());
        movies.setTitle(moviesDto.getTitle());
        movies.setDescription(moviesDto.getDescription());
        movies.setLanguage(moviesDto.getLanguage());
        movies.setGenre(moviesDto.getGenre());
        movies.setReleaseDate(moviesDto.getReleaseDate());
        movies.setDuration(moviesDto.getDurationMinutes());
        movies.setPosterUrl(moviesDto.getPosterUrl());
        return movies;
    }


}
