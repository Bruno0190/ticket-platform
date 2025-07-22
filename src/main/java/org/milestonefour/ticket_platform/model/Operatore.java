package org.milestonefour.ticket_platform.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Operatore {

    /*Attributi */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nome obbligatorio")
    private String name;

    @NotBlank(message = "e-mail obbligatoria")
    private String email;

    @NotNull
    private Boolean available;

    /*Relazioni con altre entità */
    @OneToMany(mappedBy = "operator")
    private List<Ticket> tickets;



    /*Costruttore vuoto, standard JPA */
    public Operatore(){

    }

    /*I getter */
    public Long getId(){

        return id;
    }

    public String getName(){

        return name;
    }

    public String getEmail(){

        return email;
    }

    public Boolean getAvailable(){

        return available;
    }

    public List<Ticket> getTickets(){

        return tickets;
    }



    /*I setter */
    public void setName(String name){

        this.name = name;
    }

    public void setEmail(String email){

        this.email = email;
    }

    public void setAvailable(Boolean available){

        this.available = available;
    }

    public void setTickets(List<Ticket> tickets){

        this.tickets = tickets;
    }
}
