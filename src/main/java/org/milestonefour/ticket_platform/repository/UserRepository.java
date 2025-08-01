package org.milestonefour.ticket_platform.repository;
import java.util.Optional;

import org.milestonefour.ticket_platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
