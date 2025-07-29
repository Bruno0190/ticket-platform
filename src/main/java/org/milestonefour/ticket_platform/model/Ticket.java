package org.milestonefour.ticket_platform.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;



@Entity
public class Ticket {

    /*Attributi */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "titolo obbligatorio")
    @Size(max = 20, message = "Max 20 caratteri")
    private String title;

    @Size(max = 100, message = "Max 100 caratteri")
    private String description;

    /*Per lo stato ho utilizzato la classe enum che mi consente di dare solo specifici valori a quell'attributo che poi tipizzo come Status. L'annotazione Enum(Enum.Type.STRING) serve a dire che nel database vogliamo leggere effettivamente come stringhe DA_FARE, IN_CORSO, COMPLETATO */
    public enum Status{DA_FARE, IN_CORSO, COMPLETATO};
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @NotNull
    @CreationTimestamp /*Utilizzando Hibernate posso usare questa annotazione per indicare che tale attributo debba impostarsi automaticamente */
    private LocalDate createdAt;

    /*Relazioni con altre entità */
    @ManyToOne
    @JoinColumn(name="operator_id", nullable = false)
    private Operatore operator;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Categoria category;

    @OneToMany(mappedBy = "ticket")
    private List<Nota> notes;
    

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

    public Operatore getOperator(){

        return operator;
    }

    public Categoria getCategory(){

        return category;
    }

    public List<Nota> getNotes(){

        return notes;
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

    public void setOperator(Operatore operator){

        this.operator = operator;
    }

    public void setCategory(Categoria category){

        this.category = category;
    }

    public void setNotes(List<Nota> notes){

        this.notes = notes;
    }
}


