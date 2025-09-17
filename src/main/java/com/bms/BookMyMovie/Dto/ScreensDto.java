package com.bms.BookMyMovie.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScreensDto {
    private Long screensId;
    private String screenName;
    private Integer totalSeats;
    private TheatresDto theatres;
}
