package com.bms.BookMyMovie.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

//way to reach out the error
public class ErrorResponse {
    private Date timeStamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
