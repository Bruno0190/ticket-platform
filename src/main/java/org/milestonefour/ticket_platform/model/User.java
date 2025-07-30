package org.milestonefour.ticket_platform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;



@Entity
@Table(name = "`user`")
public class User {

    /*Attributi */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome obbligatorio")
    @Size(max = 20, message = "Max 20 caratteri")
    private String username;

    @NotBlank(message = "password obbligatoria")
    private String password;

    

    /*costruttore vuoto necessario standard JPA */
    public User(){

    };

    /*Relazioni con altre entità. Qui abbiamo una relazione molti a molti, per cui occorre una tabella ponte creata con JoinTable, battezzata come user_role in cui user_id e di riflesso role_id saranno i collegamenti */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;


    /*I getter */

    public Long getId(){
        return id;

    }

    public String getUsername(){
        return username;

    }

    public String getPassword(){
        return password;

    }

    public List<Role> getRoles(){
        return roles;

    }

    /*I setter */
    public void setUsername(String username){
        this.username = username;

    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setRoles(List<Role> roles){
        this.roles = roles;

    }



}
