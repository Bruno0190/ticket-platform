package org.milestonefour.ticket_platform.repository;

import org.milestonefour.ticket_platform.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {

    List<Nota> findAllByTicketId(Long ticketId);


}
