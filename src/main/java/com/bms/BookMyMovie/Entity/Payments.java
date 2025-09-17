package com.bms.BookMyMovie.Entity;

import com.bms.BookMyMovie.Enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true)
    private String transactionId;

    @Column(nullable = false)
    private Double amount ;

    @Column(nullable = false)
    private LocalDateTime paymentTime;

    @Column(nullable = false)
    private String paymentMethod ;

    @Column(nullable = false)
    private String status;

    @OneToOne(mappedBy = "payment_id")
    private Bookings bookings;


}
