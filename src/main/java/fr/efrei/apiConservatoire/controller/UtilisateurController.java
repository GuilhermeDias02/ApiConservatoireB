package fr.efrei.apiConservatoire.controller;

import fr.efrei.apiConservatoire.model.Utilisateur;
import fr.efrei.apiConservatoire.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/utilisateur")
@RestController
@EnableMethodSecurity
public class UtilisateurController {
    private final UtilisateurService service;

    public UtilisateurController(UtilisateurService service){
        this.service = service;
    }

    @GetMapping("/{email}")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable String email){
        Utilisateur utilisateur = service.findUtilisateurByEmail(email);

        if(utilisateur != null){
            return new ResponseEntity<>(service.findUtilisateurByEmail(email), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
