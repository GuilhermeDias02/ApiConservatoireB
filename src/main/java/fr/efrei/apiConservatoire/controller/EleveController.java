package fr.efrei.apiConservatoire.controller;

import fr.efrei.apiConservatoire.service.EleveService;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/eleve")
@RestController
@EnableMethodSecurity
public class EleveController {
    private final EleveService service;

    public EleveController (EleveService eleveService){
        this.service = eleveService;
    }
}
