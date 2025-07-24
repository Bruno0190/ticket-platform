package org.milestonefour.ticket_platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



/*In questo Controller riferito alla Home page, diamo annotazione che l'intera classe gestisca URL che iniziano da "/" */
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("")
    public String home() {
        return "index";  // il file templates/index.html
    }

    @GetMapping("/tickets")
    public String indexTicket() {
        return "tickets/index";
    }

    @GetMapping("/categorie")
    public String indexCategory() {
        return "categorie/index";
    }

    @GetMapping("/operatori")
    public String indexOperators() {
        return "operatori/index";
    }
    

}
