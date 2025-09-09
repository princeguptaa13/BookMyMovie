package com.bms.BookMyMovie.Repository;

import com.bms.BookMyMovie.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users , Long> {

    Optional<Users> findByEmail(String email);

    Boolean existingByEmail(String email);


}
