package com.hcl.TicketBookingSystem.Repository;

import com.hcl.TicketBookingSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 🔎 Optional JPQL Methods (if needed later)

    // Example: find user by email
    // Optional<User> findByEmail(String email);

}