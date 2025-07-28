package org.milestonefour.ticket_platform.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;


/*l'annotazione seguente dice a Spring che questa classe dà delle istruzioni su come gestire qualcosa. In questo caso la sicurezza. Una classe così annotata coniente sempre dei metodi annotati come Bean */
@Configuration
public class SecurityConfiguration {

    /*Con Bean in questo caso dichiariamo che l'oggetto SecurityFilterChain deve essere creato una volta sola e tenerlo in memoria così da essere pronto all'utilizzo */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)
    throws Exception {
        /*http.authorizeHttpRequests è un metodo che accetta una lambda (->) che non deve contenere return al suo interno. Utilizzando lambda possiamo poi fare una catena di metodi come si vede */
        http.authorizeHttpRequests(requests -> requests
            // Solo chi ha ruolo USER può accedere a /user
            .requestMatchers("/user").hasAuthority("USER")
            // Solo chi ha ruolo ADMIN può accedere a /admin
            .requestMatchers("/admin").hasAuthority("ADMIN")
            // Tutti possono vedere la home "/"
            .requestMatchers("/").permitAll()
            // Tutti possono vedere tutto il resto "/"
            .requestMatchers("/**").permitAll()
        )
        // Mostra form login standard di Spring
        .formLogin(Customizer.withDefaults());
        
        /*Il seguente return monta l'oggetto di sopra con tutti i metodi necessari*/
        return http.build();
        
        
    }

}
