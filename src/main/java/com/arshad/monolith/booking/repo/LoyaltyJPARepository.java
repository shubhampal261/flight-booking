package com.arshad.monolith.booking.repo;

import com.arshad.monolith.booking.beans.Loyalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoyaltyJPARepository extends JpaRepository<Loyalty, Integer>  {
    Optional<Loyalty> findByUserId(final int userId);
}

