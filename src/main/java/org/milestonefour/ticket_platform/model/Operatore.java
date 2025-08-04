package org.milestonefour.ticket_platform.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "`operatore`")
public class Operatore {

    /*Attributi */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nome obbligatorio")
    private String name;

    @NotBlank(message = "e-mail obbligatoria")
    private String email;

    public enum StatoOperatore { ACTIVE, NO_ACTIVE }
    @Enumerated(EnumType.STRING)
    @NotNull
    private StatoOperatore statoOperatore;


    @NotNull
    private Boolean available;

    /*Relazioni con altre entità */
    @OneToMany(mappedBy = "operator")
    private List<Ticket> tickets;

    @OneToOne(mappedBy = "operatore")
    private User user;


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

    public Boolean getAvailable() {

        return available;
    }

    public StatoOperatore getStatoOperatore(){
        
        return statoOperatore;
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

    public void setAvailable(Boolean available) {

        this.available = available;
    }

    public void setStatoOperatore(StatoOperatore statoOperatore){

        this.statoOperatore = statoOperatore;
    }

    public void setTickets(List<Ticket> tickets){

        this.tickets = tickets;
    }
}
