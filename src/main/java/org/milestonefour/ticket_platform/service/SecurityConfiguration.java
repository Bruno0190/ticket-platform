package org.milestonefour.ticket_platform.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;


/*l'annotazione seguente dice a Spring che questa classe dà delle istruzioni su come gestire qualcosa. In questo caso la sicurezza. Una classe così annotata contiene sempre dei metodi annotati come Bean e che Spring chiama in autonomia */
@Configuration
public class SecurityConfiguration {

    /*Con Bean in questo caso dichiariamo che l'oggetto SecurityFilterChain deve essere creato una volta sola e tenerlo in memoria così da essere pronto all'utilizzo */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)
    /*I metodi usati su oggetti di tipo HttpSecurity lanciano potenziali eccezioni, ecco perchè non ve ne sono di effettivamente dichiarate */
    throws Exception {
        /*http.authorizeHttpRequests è un metodo che accetta una lambda (->) che non deve contenere return al suo interno. Utilizzando lambda possiamo poi fare una catena di metodi come si vede. Le lambda sono modi brevi per scrivere metodi senza nome */
        http.authorizeHttpRequests(requests -> requests
            
        .requestMatchers("/tickets", "/tickets/index", "/tickets/show/**", "/tickets/create", "/tickets/edit/**").hasAnyAuthority("ADMIN", "OPERATOR")

            .requestMatchers("/operatori", "/operatori/**")
                .hasAuthority("ADMIN")

            .requestMatchers("/categorie", "/categorie/**")
                .hasAuthority("ADMIN")

            .requestMatchers("/profilo", "/profilo/index")
                .hasAnyAuthority("ADMIN", "OPERATOR")

            // Homepage accessibile a tutti
            .requestMatchers("/").permitAll()

            // Tutto il resto: solo utenti loggati
            .anyRequest().authenticated()
            

            // Homepage accessibile a tutti
            .requestMatchers("/").permitAll()

            // Tutto il resto: solo utenti loggati
            .anyRequest().authenticated()
        );
        http.formLogin(Customizer.withDefaults())  //login standard
        .logout(logout -> logout
            .logoutSuccessUrl("/login?logout")
            .permitAll()
        );

        
        /*Il seguente return monta l'oggetto di sopra con tutti i metodi necessari*/
        return http.build();
        
    }

    /*Il seguente metodo usa un istanza della classe DatabaseUserDetailsService in pratica per recuperare l'utente da database*/
    @Bean
    DatabaseUserDetailsService userDetailsService(){
        return new DatabaseUserDetailsService();
    }

    /*Quest'altro metodo invece serve a criptare e verificare password tramite lo standard BCrypt */
    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /*E infine questo metodo collega userDetailsService e passwordEncoder per fare funzionare il login */
    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());

        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}





/* Prima al posto dei primi 4 .requestMatchers vi era scritto quanto sotto, che sono le autorizzazioni alle rotte fittizie di default
Solo chi ha ruolo USER può accedere a /user
.requestMatchers("/user").hasAuthority("USER")
Solo chi ha ruolo ADMIN può accedere a /admin
.requestMatchers("/admin").hasAuthority("ADMIN")
*/