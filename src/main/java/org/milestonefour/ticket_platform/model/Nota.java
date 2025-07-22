package org.milestonefour.ticket_platform.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.JoinColumn;

@Entity
public class Nota {

    /*Attributi */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50, message = "Max 50 caratteri")
    private String text;

    @NotBlank
    private String author;

    @CreationTimestamp
    private LocalDate createdAt;

    /*Relazioni con altre entità */
    @ManyToOne
    @JoinColumn(name="ticket_id", nullable=false)
    private Ticket ticket;

    /*costruttore vuoto necessario standard JPA */
    public Nota(){

    }

    /*I getter */
    public Long getId(){

        return id;
    }

    public String getText(){

        return text;
    }

    public String getAuthor(){

        return author;
    }

    public LocalDate getCreatedAt(){

        return createdAt;
    }

    public Ticket getTicket(){

        return ticket;
    }



    /*I setter */
    public void setText(String text){

        this.text = text;
    }

    public void setAuthor(String author){

        this.author = author;
    }

    public void setTicket(Ticket ticket){

        this.ticket = ticket;
    }


}
