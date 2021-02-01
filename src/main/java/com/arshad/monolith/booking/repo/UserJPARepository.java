package com.arshad.monolith.booking.repo;

import com.arshad.monolith.booking.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<User, Integer>  {
}

