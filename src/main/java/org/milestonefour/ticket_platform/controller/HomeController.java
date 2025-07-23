package org.milestonefour.ticket_platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;





/*In questo Controller riferito alla Home page, diamo annotazione che l'intera classe gestisca URL che iniziano da "/" */
@Controller
@RequestMapping("/")
public class HomeController {

    /*Il metodo qui subito sotto deve partire da "/" quindi non occorre specificare altro nell'argomento */
    @GetMapping("")
    public String index() {
        return "index";
    }
    

}
