package com.bms.BookMyMovie.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TheatresDto {
    private Long theatreId;
    private String theatreName;
    private String address ;
    private String city ;
    private Integer totalScreens;
}
