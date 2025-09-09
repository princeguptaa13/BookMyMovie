package com.bms.BookMyMovie.Repository;

import com.bms.BookMyMovie.Entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payments , Long> {
    Optional<Payments> findByTransactionId(String transactionId);
}
