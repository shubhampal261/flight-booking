package com.arshad.monolith.booking.repo;

import com.arshad.monolith.booking.beans.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingJPARepository extends JpaRepository<Booking, Integer>  {
}

