package org.milestonefour.ticket_platform.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.milestonefour.ticket_platform.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.milestonefour.ticket_platform.model.Ticket;


@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {

    @Autowired 
    private TicketRepository ticketRepository;

    /*Per visualizzare l'elenco dei Ticket in formato JSON */
    @GetMapping("")
    public List<Ticket> ticketList(){
        return ticketRepository.findAll();
    }

    @GetMapping("/categorie/{id}")
    public List<Ticket> ticketFilteredCategory(@PathVariable("id") Long id) {
        return ticketRepository.findAllByCategoryId(id);
    }

    @GetMapping("/{status}")
    public List<Ticket> ticketFilteredStatus(@PathVariable("status") Ticket.Status status) {
        return ticketRepository.findAllByStatus(status);
    }
    
    
}
