package org.milestonefour.ticket_platform.controller;

import org.milestonefour.ticket_platform.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("categorie/")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("")
    public String CategoriaIndex(Model model){

        model.addAttribute("categorie", categoriaRepository.findAll());
        
        return "categorie/index";

    }

}
