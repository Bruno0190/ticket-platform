package org.milestonefour.ticket_platform.repository;

import java.util.Optional;

import org.milestonefour.ticket_platform.model.Operatore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatoreRepository extends JpaRepository<Operatore, Long> {

    Optional<Operatore> findByName(String name);
}
