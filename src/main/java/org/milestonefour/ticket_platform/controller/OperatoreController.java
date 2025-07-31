package org.milestonefour.ticket_platform.controller;


import org.milestonefour.ticket_platform.repository.OperatoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/operatori")
public class OperatoreController{

    @Autowired
    private OperatoreRepository operatoreRepository;



    @GetMapping("")
    public String index(Model model){
        model.addAttribute("operatori", operatoreRepository.findAll());

        return "operatori/index";
    }

}
