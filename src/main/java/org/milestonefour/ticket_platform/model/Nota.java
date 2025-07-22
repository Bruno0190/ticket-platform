package org.milestonefour.ticket_platform.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50, message = "Max 50 caratteri")
    private String text;

    @NotBlank
    private String author;

    private LocalDate createdAt;

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

    /*I setter */
    public void setText(String text){

        this.text = text;
    }

    public void setAuthor(String author){

        this.author = author;
    }

    public void setCreatedAt(LocalDate createdAt){

        this.createdAt = createdAt;
    }

}
