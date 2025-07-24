package org.milestonefour.ticket_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.milestonefour.ticket_platform.model.Categoria;
import org.milestonefour.ticket_platform.model.Operatore;
import org.milestonefour.ticket_platform.model.Ticket;
import org.milestonefour.ticket_platform.repository.CategoriaRepository;
import org.milestonefour.ticket_platform.repository.OperatoreRepository;
import org.milestonefour.ticket_platform.repository.TicketRepository;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    /*Per convenzione le variabili oggetto repository vengono posizionati prima dei metodi URL. Tali oggetti vengono iniettati automaticamente con annotazione autowired */
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private OperatoreRepository operatoreRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;


    @GetMapping("")
    public String index(Model model){
        model.addAttribute("tickets", ticketRepository.findAll());
        return "tickets/index";
    }

    /*Model è una classe di Spring che istanzia oggetti per passarli agli HTML*/
    @GetMapping("/create")
    public String create(Model model){
        /*Il metodo addAttribute, attribuisce letteralmente un nome (un tag) all'oggetto che diventa così il modello di riferimento disponibile nel form HTML che ritorniamo. Così i dati inseriti nell'oggetto ne diventano le sue caratteristiche e questo viene rimandato completo al controller */
        model.addAttribute("ticket", new Ticket());

        /*Mi occorre aggiungere al form gli oggetti dalle Entity Categoria e Operatore, li recupero dalle repository */
        model.addAttribute("categorie", categoriaRepository.findAll());
        model.addAttribute("operatori", operatoreRepository.findAll());

        return "tickets/create";
    }

    @PostMapping("/create")
    /*@Valid funziona perchè nella classe Ticket ho messo che gli attributi o campi devono rispettare certe regole come @NotBlank. */
    public String store(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingresult, Model model){
        /*bindingresult è un oggetto della classe BindingResult che ha come scopo verificare se sono validati i dati. Il seguente blocco if riporta alla pagina segnando gli errori, riaggiornando anche le liste di catgorie e operatori */
        if(bindingresult.hasErrors()){
            model.addAttribute("categorie", categoriaRepository.findAll());
            model.addAttribute("operatori", operatoreRepository.findAll());
            return "/tickets/create";
        }

        ticketRepository.save(ticket);
        /*stessa cosa che scrivere return "tickets/create"*/
        return "redirect:/tickets";
    }

}
