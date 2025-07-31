package org.milestonefour.ticket_platform.controller;

import java.security.Principal;
import org.milestonefour.ticket_platform.model.Ticket;
import org.milestonefour.ticket_platform.model.Operatore.StatoOperatore;
import org.milestonefour.ticket_platform.model.Operatore;
import java.util.Optional;
import org.milestonefour.ticket_platform.repository.CategoriaRepository;
import org.milestonefour.ticket_platform.repository.OperatoreRepository;
import org.milestonefour.ticket_platform.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import org.milestonefour.ticket_platform.model.Ticket.Status;


/*In questo Controller riferito alla Home page, diamo annotazione che l'intera classe gestisca URL che iniziano da "/" */
@Controller
@RequestMapping("/")
public class HomeController {


    @Autowired
    private OperatoreRepository categoriaRepository;
    @Autowired
    private OperatoreRepository operatoreRepository;



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
        Optional<Operatore> optOperatore = operatoreRepository.findByName(username);
        Operatore operatore = optOperatore.orElse(null); 
        model.addAttribute("operatore", operatore);

        return "operatori/index";
    }

    @PostMapping("/profilo")
    public String update(@ModelAttribute("operatore") Operatore operatore, Model model){

        model.addAttribute("tickets", operatore.getTickets());

        Operatore realOperatore = operatoreRepository.findById(operatore.getId()).get();

        List<Ticket> tickets = realOperatore.getTickets();

        for(Ticket tick : tickets){

            if (tick.getStatus() == Status.DA_FARE || tick.getStatus() == Status.IN_CORSO ){
            model.addAttribute("errore", "Non puoi disattivarti finché hai ticket attivi.");
            return "operatori/index";

            } else {
                realOperatore.setStatoOperatore(StatoOperatore.NO_ACTIVE);

                operatoreRepository.save(realOperatore);
                
                model.addAttribute("operatore", realOperatore);
                model.addAttribute("tickets", realOperatore.getTickets());

                return "operatori/index";

            }
        }
        return "operatori/index";
        
    }

}
