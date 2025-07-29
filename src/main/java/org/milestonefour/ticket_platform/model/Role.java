package org.milestonefour.ticket_platform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToMany;
import java.util.List;

@Entity
public class Role {

    /*Attributi */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleTitle;

    /*costruttore vuoto necessario standard JPA */
    public Role(){

    }

    /*Relazioni con altre entità. Avendo già creato la tabella ponte dal lato User, da questo lato ci colleghiamo tramite l'attributo roles di User quindi senza creare un altra tabella pone ma sfruttando la stessa */
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    /*I getter */
    public String getTitle(){
        return roleTitle;
    }

    public List<User> getUsers(){
        return users;

    }

    /*I setter */
    public void setRole(String roleTitle){
        this.roleTitle = roleTitle;
    }

    public void setUsers(List<User> users){
        this.users = users;

    }

    


}
