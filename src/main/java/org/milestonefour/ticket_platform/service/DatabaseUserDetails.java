package org.milestonefour.ticket_platform.service;
/*Nel package service che abbiamo creato vanno incluse le classi di logica applicativa. Ovvero quelle classi gestiscono provessi di validazioni complesse, calcoli e connessione a sistemi esterni o gestione della sicurezza. Queste classi non sono model e non sono controller. */
import java.util.HashSet;
import java.util.Set;

import org.milestonefour.ticket_platform.model.User;
import org.milestonefour.ticket_platform.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;


/*Con implements si intende che la classe corrente riporta concretamente i metodi che UserDetails richiede. */
public class DatabaseUserDetails implements UserDetails{

    /*Gli attributi in questa classe oltre che essere privati sono immutabili con final */
    private final Long id;
    private final String username;
    private final String password;
    /*Si usa Set come tipo di Collection, perchè Set non permette duplicati. GrantedAuthority è un interfaccia propria di Spring Security che rappresenta un permesso o un ruolo utente */
    private final Set<GrantedAuthority> authorities;

    public DatabaseUserDetails(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        /*Tra le caratteristiche del costruttore authorities è dato da un HashSet(classe che implementa Set mettendo gli oggetti in coppie chiave-valore.) */
        authorities = new HashSet<GrantedAuthority>();
        for(Role role : user.getRoles()) {
            /*SimpleGrantedAuthority è una classe che implementa GrantedAuthority e rappresenta un singolo ruolo  */
            authorities.add(new SimpleGrantedAuthority(role.getTitle()));
        }
    }

    /*Di seguito tutti i metodi di UserDetails che devono obbligatoriamente essere riportati e sovrascritti con Override */

    @Override /*Le Collection sono collezioni astratti di oggetti, possono essere List o Set o Queue. Il punto interrogativo significa "una qualunque classe figlia di GrantedAuthority" */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*Pertanto ciò che viene ritornato è una collezione di oggetti authorities che estendono GrantedAuthority */
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
