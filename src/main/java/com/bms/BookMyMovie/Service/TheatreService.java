package com.bms.BookMyMovie.Service;

import com.bms.BookMyMovie.Dto.TheatresDto;
import com.bms.BookMyMovie.Entity.Theatre;
import com.bms.BookMyMovie.Exception.ResouceNotFoundException;
import com.bms.BookMyMovie.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;

    public TheatresDto createTheatre(TheatresDto theatresDto){
      Theatre theatre = mapToEntity(theatresDto);
        Theatre saved = theatreRepository.save(theatre);
        return mapToDto(saved);

    }

    public TheatresDto getTheatreById(Long id){
        Theatre theatre = theatreRepository.findById(id)
                .orElseThrow(()-> new ResouceNotFoundException("Theatre not found with id : "+id));
        return mapToDto(theatre);
    }

    public List<TheatresDto> getAllTheatre(){
        List<Theatre> theatre = theatreRepository.findAll();
        return theatre.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<TheatresDto> getTheatreByCity(String city){
        List<Theatre> theatre = theatreRepository.findByCity(city);
        return theatre.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    //delete Theatre
    public void deleteTheatre(Long id){
        Theatre theatre = theatreRepository.findById(id)
                .orElseThrow(()-> new ResouceNotFoundException("Theatre not found with id : "+id));
        theatreRepository.delete(theatre);
    }


    private TheatresDto mapToDto(Theatre theatre) {
        TheatresDto theatresDto =  new TheatresDto();
        theatresDto.setTheatreId(theatre.getId());
        theatresDto.setTheatreName(theatre.getName());
        theatresDto.setCity(theatre.getCity());
        theatresDto.setAddress(theatre.getAddress());
        theatresDto.setTotalScreens(theatre.getTotalScreens());
        return theatresDto;
    }

    public Theatre mapToEntity(TheatresDto theatresDto){
        Theatre theatre = new Theatre();
        theatre.setName(theatresDto.getTheatreName());
        theatre.setAddress(theatresDto.getAddress());
        theatre.setCity(theatresDto.getCity());
        theatre.setTotalScreens(theatresDto.getTotalScreens());
        return theatre;

    }
}
