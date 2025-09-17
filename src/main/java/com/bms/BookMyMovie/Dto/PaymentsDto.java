package com.bms.BookMyMovie.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentsDto {

    private Long id;
    private String transactionID;
    private Double Amount;
    private LocalDateTime paymentTime;
    private String status;
    private String paymentMethod;

}
