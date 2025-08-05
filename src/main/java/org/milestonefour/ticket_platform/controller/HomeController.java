package org.milestonefour.ticket_platform.controller;

import java.security.Principal;
import org.milestonefour.ticket_platform.model.Ticket;
import org.milestonefour.ticket_platform.model.Operatore.StatoOperatore;
import org.milestonefour.ticket_platform.model.Operatore;
import java.util.Optional;
import org.milestonefour.ticket_platform.repository.CategoriaRepository;
import org.milestonefour.ticket_platform.repository.OperatoreRepository;
import org.milestonefour.ticket_platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.milestonefour.ticket_platform.model.Ticket.Status;
import org.milestonefour.ticket_platform.model.User;

/*In questo Controller riferito alla Home page, diamo annotazione che l'intera classe gestisca URL che iniziano da "/" */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private OperatoreRepository operatoreRepository;
    @Autowired
    private UserRepository userRepository;



    @GetMapping("")
    public String home() {
        return "index";
    }

    @GetMapping("/categorie")
    public String indexCategory(Model model) {
        model.addAttribute("categorie", categoriaRepository.findAll());


        return "categorie/index";
    }


    /*Nell'argomento di questo metodo abbiamo la classe Principal. Un oggetto Principal è quello che si crea quando qualcuno fa login in pagina, Cioè l'identità del loggato è un oggetto Principal */
    @GetMapping("/profilo")
    public String indexOperators(Model model, Principal principal) {
        String username = principal.getName();
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent() && user.get().getOperatore() != null) {
            Operatore operatore = user.get().getOperatore();
            model.addAttribute("operatore", operatore);
        } else {
            model.addAttribute("errore", "Nessun operatore collegato a questo utente.");
        }

        return "profilo/index";
    }


    @PostMapping("/profilo")
    public String update(@RequestParam("statoOperatore") StatoOperatore nuovoStato, Model model, Principal principal) {

        // Ottengo username utente loggato
        String username = principal.getName();

        // Recupero l'oggetto User usando lo username
        User user = userRepository.findByUsername(username).get();

        // Recupero operatore legato a questo User
        Operatore operatore = user.getOperatore();

        // Controllo se ci sono ticket attivi
        boolean haTicketAttivi = false;

        for (Ticket ticket : operatore.getTickets()) {
            if (ticket.getStatus() == Status.DA_FARE || ticket.getStatus() == Status.IN_CORSO) {
                haTicketAttivi = true;
                break;
            }
        }

        // Se vuole disattivarsi ma ha ticket attivi, ritorno un messaggio di errore
        if (haTicketAttivi && nuovoStato == StatoOperatore.NO_ACTIVE) {
            model.addAttribute("operatore", operatore);
            model.addAttribute("errore", "Non puoi disattivarti finché hai ticket attivi.");
            return "profilo/index";
        }

        // Altrimenti aggiorno lo stato
        operatore.setStatoOperatore(nuovoStato);
        operatoreRepository.save(operatore);

        // mando conferma di successo
        model.addAttribute("operatore", operatore);
        model.addAttribute("successo", "Stato aggiornato con successo.");
        return "profilo/index";
    }



}
