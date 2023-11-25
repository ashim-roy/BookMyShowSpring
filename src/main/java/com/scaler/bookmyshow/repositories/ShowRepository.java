package com.scaler.bookmyshow.repositories;

import com.scaler.bookmyshow.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show, Long> {  // deals with data access and database operations
    @Override
    Optional<Show> findById(Long aLong);
}
