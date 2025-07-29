package org.milestonefour.ticket_platform.service;

import org.milestonefour.ticket_platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;


import org.milestonefour.ticket_platform.model.User;

/*Questa è una classe di servizio e verrà iniettata dove occorre. Inoltre implementa il comportamento della classe UserDetailsService. Nel fare questo è necessario implementare il metodo loadUserByUsername*/
@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /*Il metodo seguente è quello che Spring chiama automaticamente quando si tenta l'accesso. Infatti il parametro è un username di oggetto User e se esiste viene ritornato un oggetto DatabaseUserDetails */
    @Override
    public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
        /*Optional è una collection che potrebbe rivelarsi vuota */
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()){
            return new DatabaseUserDetails(user.get());
        } else {
            throw new UsernameNotFoundException("Username not found");
        }
    }

}
