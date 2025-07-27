package org.milestonefour.ticket_platform.repository;

import java.util.List;

import org.milestonefour.ticket_platform.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findAllByCategoryId(Long categoryId);

    /*Avendo già definito Status nella classe Ticket, il tipo di status di cui dobbiamo tenere conto è proprio un Ticket.Status */
    List<Ticket> findAllByStatus(Ticket.Status status);

}
