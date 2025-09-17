package com.bms.BookMyMovie.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowsDto {
    private Long showId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private MoviesDto movies;
    private ScreensDto screensDto;
    private List<ShowsDto> availableSeats;
}
