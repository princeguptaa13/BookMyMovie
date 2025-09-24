package com.bms.BookMyMovie.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MoviesDto {
    private Long movieId;
    private String title;
    private String language;
    private String description;
    private String genre;
    private Integer durationMinutes;
    private String releaseDate;
    private String posterUrl;
}
