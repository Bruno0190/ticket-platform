package org.milestonefour.ticket_platform.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "titolo obbligatorio")
    @Size(max = 20, message = "Max 20 caratteri")
    private String title;

    @Size(max = 100, message = "Max 100 caratteri")
    private String description;

    /*Per lo stato ho utilizzato la classe enum che mi consente di dare solo specifici valori a quell'attributo che poi tipizzo come Status. L'annotazione Enum(Enum.Tupe.STRING) serve a dire che nel database vogliamo leggere effettivamente come stringhe active o no_active */
    public enum Status{active, no_active};
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @NotNull
    @CreationTimestamp /*Utilizzando Hibernate posso usare questa annotazione per indicare che tale attributo debba impostarsi automaticamente */
    private LocalDate createdAt;

    /*costruttore vuoto necessario standard JPA */
    public Ticket(){
    }


    /*I getter */
    public Long getId(){

        return id;
    }

    public String getTitle(){

        return title;
    }

    public String getDescription(){

        return description;
    }

    public Status getStatus(){

        return status;
    }

    public LocalDate getCreatedAt(){

        return createdAt;
    }

    /*I setter */
    public void setTitle(String title){

        this.title = title;
    }

    public void setDescription(String description){

        this.description = description;
    }

    public void setStatus(Status status){

        this.status = status;
    }

    /*Il setter di createdAt non c'è perchè non la impostiamo manualmente ma automaticamente con hibernate */
}


