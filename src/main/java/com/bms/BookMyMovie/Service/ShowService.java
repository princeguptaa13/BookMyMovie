package com.bms.BookMyMovie.Service;

import com.bms.BookMyMovie.Dto.*;
import com.bms.BookMyMovie.Entity.Movies;
import com.bms.BookMyMovie.Entity.Screen;
import com.bms.BookMyMovie.Entity.ShowSeats;
import com.bms.BookMyMovie.Entity.Shows;
import com.bms.BookMyMovie.Exception.ResouceNotFoundException;
import com.bms.BookMyMovie.Repository.MovieRepository;
import com.bms.BookMyMovie.Repository.ScreenRepository;
import com.bms.BookMyMovie.Repository.ShowRepository;
import com.bms.BookMyMovie.Repository.ShowSeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ShowService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatsRepository showSeatsRepository;

    public ShowsDto createShow(ShowsDto showsDto){
        Shows show = new Shows();
        Movies movie = movieRepository.findById(showsDto.getMovies().getMovieId())
                .orElseThrow((()-> new ResouceNotFoundException("Movie Not Found")));
        Screen screen = screenRepository.findById(showsDto.getScreens().getScreensId())
                .orElseThrow((()-> new ResouceNotFoundException("Movie Not Found")));

        show.setMovies(movie);
        show.setScreen(screen);
        show.setStartTime(showsDto.getStartTime());
        show.setEndTime(showsDto.getEndTime());

        Shows saveShow = showRepository.save(show);

        List<ShowSeats> availableSeats =
                showSeatsRepository.findByShowIdAndStatus(saveShow.getId(), "AVAILABLE");

        return mapToDto(saveShow , availableSeats);
    }

    public ShowsDto getShowById(Long id){
        Shows show = showRepository.findById(id)
                .orElseThrow(()->new ResouceNotFoundException("Show Not Found with id "+id));
        List<ShowSeats> availableSeats=
                showSeatsRepository.findByShowIdAndStatus(show.getId() , "AVAILABLE");
        return mapToDto(show , availableSeats);
    }

    public List<ShowsDto> getAllShows(){
        List<Shows> shows = showRepository.findAll();
        return shows.stream()
                .map(shows1 -> {
                   List<ShowSeats> availableSeats =
                           showSeatsRepository.findByShowIdAndStatus(shows1.getId(), "AVAILABLE");
                    return mapToDto(shows1 , availableSeats);
                })
                .collect(Collectors.toList());
    }

    public List<ShowsDto> getMovieById(Long movieId){
        List<Shows> shows = showRepository.findByMovieId(movieId);
        return shows.stream()
                .map(shows1 -> {
                    List<ShowSeats> availableSeats =
                            showSeatsRepository.findByShowIdAndStatus(shows1.getId(), "AVAILABLE");
                    return mapToDto(shows1 , availableSeats);
                })
                .collect(Collectors.toList());
    }

    public List<ShowsDto> getShowsByMovieAndCity(Long movieId , String city){
        List<Shows> shows = showRepository.findByMovie_IdAndScreen_Theatre_City(movieId , city);
        return shows.stream()
                .map(shows1 -> {
                    List<ShowSeats> availableSeats =
                            showSeatsRepository.findByShowIdAndStatus(shows1.getId(), "AVAILABLE");
                    return mapToDto(shows1 , availableSeats);
                })
                .collect(Collectors.toList());
    }

    public List<ShowsDto> getShowsByDateRange(LocalDateTime startDate , LocalDateTime endDate){
        List<Shows> shows = showRepository.findByStartTimeBetween(startDate, endDate);
        return shows.stream()
                .map(shows1 -> {
                    List<ShowSeats> availableSeats =
                            showSeatsRepository.findByShowIdAndStatus(shows1.getId(), "AVAILABLE");
                    return mapToDto(shows1 , availableSeats);
                })
                .collect(Collectors.toList());
    }

    private ShowsDto mapToDto(Shows shows , List<ShowSeats > availableSeats){
        ShowsDto showsDto = new ShowsDto();
        showsDto.setShowId(shows.getId());
        showsDto.setStartTime(shows.getStartTime());
        showsDto.setEndTime(shows.getEndTime());

        showsDto.setMovies(new MoviesDto(
                shows.getMovies().getId(),
                shows.getMovies().getTitle(),
                shows.getMovies().getLanguage(),
                shows.getMovies().getGenre(),
                shows.getMovies().getDescription(),
                shows.getMovies().getDuration(),
                shows.getMovies().getReleaseDate(),
                shows.getMovies().getPosterUrl()
        ));

        TheatresDto theatresDto = new TheatresDto(
                shows.getScreen().getTheatre().getId(),
                shows.getScreen().getTheatre().getName(),
                shows.getScreen().getTheatre().getAddress(),
                shows.getScreen().getTheatre().getCity(),
                shows.getScreen().getTheatre().getTotalScreens()
        );

        showsDto.setScreens(new ScreensDto(
                shows.getScreen().getId(),
                shows.getScreen().getName(),
                shows.getScreen().getTotalSeats(),
                theatresDto
        ));

        List<ShowSeatsDto> seatDtos = availableSeats.stream()
                .map(seat ->{
                    ShowSeatsDto seatsDto = new ShowSeatsDto();
                    seatsDto.setId(seat.getId());
                    seatsDto.setStatus(seat.getStatus());
                    seatsDto.setPrice(seat.getPrice());

                    SeatsDto baseSeatSto = new SeatsDto();
                    baseSeatSto.setSeatsId(seat.getSeats().getId());
                    baseSeatSto.setSeatNumber(seat.getSeats().getSeatNumber());
                    baseSeatSto.setSeatCategory(seat.getSeats().getSeatCategory());
                    baseSeatSto.setBasePrice(seat.getSeats().getBasePrice());
                    seatsDto.setSeats(baseSeatSto);
                    return seatsDto;
                })
                .collect(Collectors.toList());

        showsDto.setAvailableSeats(seatDtos);
        return showsDto;
    }
}
