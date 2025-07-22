package org.milestonefour.ticket_platform.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Operatore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nome obbligatorio")
    private String name;

    @NotBlank(message = "e-mail obbligatoria")
    private String email;

    @NotNull
    private Boolean available;

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
}
