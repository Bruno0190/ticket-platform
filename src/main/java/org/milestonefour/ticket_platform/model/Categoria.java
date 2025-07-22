package org.milestonefour.ticket_platform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Categoria {

    /*Attributi */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    /*costruttore vuoto necessario standard JPA */
    public Categoria(){

    }

    /*Relazioni con altre entità */
    @OneToMany(mappedBy = "category")
    private List<Ticket> tickets;

    /*I getter */
    public Long getId(){

        return id;
    }

    public String getName(){

        return name;
    }

    public List<Ticket> getTickets(){

        return tickets;
    }

    /*I setter */
    public void setName(String name){


        this.name = name;
    }

    public void setTickets(List<Ticket> tickets){

        this.tickets = tickets;
    }
}
