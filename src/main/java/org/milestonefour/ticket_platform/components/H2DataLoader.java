package org.milestonefour.ticket_platform.components;

import org.milestonefour.ticket_platform.model.User;
import org.milestonefour.ticket_platform.model.Operatore.StatoOperatore;
import org.milestonefour.ticket_platform.model.Ticket.Status;
import org.milestonefour.ticket_platform.repository.CategoriaRepository;
import org.milestonefour.ticket_platform.repository.NotaRepository;
import org.milestonefour.ticket_platform.repository.OperatoreRepository;
import org.milestonefour.ticket_platform.repository.RoleRepository;
import org.milestonefour.ticket_platform.repository.TicketRepository;
import org.milestonefour.ticket_platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import org.milestonefour.ticket_platform.model.Categoria;
import org.milestonefour.ticket_platform.model.Operatore;
import org.milestonefour.ticket_platform.model.Role;
import org.milestonefour.ticket_platform.model.Nota;
import org.milestonefour.ticket_platform.model.Ticket;

@Component
public class H2DataLoader implements CommandLineRunner {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private OperatoreRepository operatoreRepository;
    @Autowired private CategoriaRepository categoriaRepository;
    @Autowired private TicketRepository ticketRepository;
    @Autowired private NotaRepository notaRepository;

    @Override
    public void run(String... args) throws Exception {
        // Ruoli
        Role adminRole = new Role();
        adminRole.setRole("ADMIN");

        Role operatorRole = new Role();
        operatorRole.setRole("OPERATOR");

        roleRepository.save(adminRole);
        roleRepository.save(operatorRole);

        // Categorie
        Categoria category1 = new Categoria();
        category1.setName("Modulo non funzionante");

        Categoria category2 = new Categoria();
        category2.setName("Problema mensa");

        categoriaRepository.save(category1);
        categoriaRepository.save(category2);

        // Operatori
        Operatore operator1 = new Operatore();
        operator1.setName("Giacomo");
        operator1.setEmail("giacomo.leopardi@gmail.com");
        operator1.setStatoOperatore(StatoOperatore.ACTIVE);
        operator1.setAvailable(true);

        Operatore operator2 = new Operatore();
        operator2.setName("Ugo");
        operator2.setEmail("ugo.foscolo@gmail.com");
        operator2.setStatoOperatore(StatoOperatore.ACTIVE);
        operator2.setAvailable(true);

        operatoreRepository.save(operator1);
        operatoreRepository.save(operator2);

        // Utenti (dopo operatori, così hanno ID)
        User user1 = new User();
        user1.setUsername("BobaFett");
        user1.setPassword(passwordEncoder.encode("1234abcdef@#"));
        user1.setRoles(List.of(adminRole));
        user1.setOperatore(operator1);

        User user2 = new User();
        user2.setUsername("LukeSkywalker");
        user2.setPassword(passwordEncoder.encode("5678ghijkl%&"));
        user2.setRoles(List.of(operatorRole));
        user2.setOperatore(operator2);

        userRepository.save(user1);
        userRepository.save(user2);

        // Ticket
        Ticket ticket1 = new Ticket();
        ticket1.setTitle("Problemi modulistica");
        ticket1.setDescription("Il modulo A e B inviano dati incompleti.");
        ticket1.setStatus(Status.DA_FARE);
        ticket1.setCategory(category1);
        ticket1.setOperator(operator1);
        ticket1.setCreatedAt(LocalDate.now());

        Ticket ticket2 = new Ticket();
        ticket2.setTitle("Mensa chiusa");
        ticket2.setDescription("La mensa non eroga pasti da 3 giorni.");
        ticket2.setStatus(Status.IN_CORSO);
        ticket2.setCategory(category2);
        ticket2.setOperator(operator2);
        ticket2.setCreatedAt(LocalDate.now());

        ticketRepository.save(ticket1);
        ticketRepository.save(ticket2);

        // Note
        Nota nota1 = new Nota();
        nota1.setText("Modulo non invia i dati correttamente.");
        nota1.setAuthor("Giacomo Leopardi");
        nota1.setTicket(ticket1);

        Nota nota2 = new Nota();
        nota2.setText("Modulo invia dati a indirizzi errati.");
        nota2.setAuthor("Giacomo Leopardi");
        nota2.setTicket(ticket1);

        Nota nota3 = new Nota();
        nota3.setText("Mensa non operativa.");
        nota3.setAuthor("Ugo Foscolo");
        nota3.setTicket(ticket2);

        Nota nota4 = new Nota();
        nota4.setText("Problema risolto parzialmente.");
        nota4.setAuthor("Ugo Foscolo");
        nota4.setTicket(ticket2);

        notaRepository.saveAll(List.of(nota1, nota2, nota3, nota4));

        // Console di conferma
        System.out.println("----- UTENTI SALVATI -----");
        for (User u : userRepository.findAll()) {
            System.out.println(" - Username: " + u.getUsername());
        }
    }

}
