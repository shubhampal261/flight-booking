package com.arshad.monolith.booking.repo;

import com.arshad.monolith.booking.beans.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightJPARepository extends JpaRepository<Flight, Integer>  {
}

