package fr.efrei.apiConservatoire.controller;

import fr.efrei.apiConservatoire.dto.CreateEleve;
import fr.efrei.apiConservatoire.dto.UpdateEleve;
import fr.efrei.apiConservatoire.model.Classe;
import fr.efrei.apiConservatoire.model.Eleve;
import fr.efrei.apiConservatoire.model.Utilisateur;
import fr.efrei.apiConservatoire.service.ClasseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/classe")
@RestController
@EnableMethodSecurity
public class ClasseController {
    private final ClasseService service;
//    private final UtilisateurService utilisateurService;

    public ClasseController (ClasseService service/*, UtilisateurService utilisateurService*/){
        this.service = service;
//        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public ResponseEntity<List<Classe>> getAllClasses(){
        return new ResponseEntity<>(service.findAllClasses(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/one/{uuid}")
    public ResponseEntity<Eleve> getEleve(@PathVariable String uuid){
        Eleve eleve = service.findEleveByUuid(uuid);

        if(eleve != null){
            return new ResponseEntity<>(service.findEleveByUuid(uuid), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('Admin') or hasRole('Prof') or hasRole('Parent')")
    @GetMapping("/utilisateur/{uuid}")
    public ResponseEntity<List<Eleve>> getElevesUtilisateur(@PathVariable String uuid){
        Utilisateur utilisateur = utilisateurService.findUtilisateurByUuid(uuid);

        if (utilisateur != null){
            List<Eleve> elevesUtilisateur = service.findAllEleveByUtilisateur(utilisateur);
            return new ResponseEntity<>(elevesUtilisateur, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('Admin') or hasRole('Prof') or hasRole('Parent')")
    @PostMapping
    public ResponseEntity<Eleve> postEleve(@Valid @RequestBody CreateEleve eleve){
        Eleve eleveACreer = service.createEleve(eleve);
        return new ResponseEntity<>(eleveACreer, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteEleve(@PathVariable String uuid){
        boolean isDeleted = service.deleteEleve(uuid);
        if(isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{uuid}")
    public ResponseEntity<?> putEleve(@PathVariable String uuid, @Valid @RequestBody UpdateEleve eleve){
        boolean isUpdated = service.updateEleve(uuid, eleve);
        if(isUpdated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // c'est des bool donc je pourrais prendre un
    @PreAuthorize("hasRole('Admin') or hasRole('Parent') or hasRole('Prof')")
    @PatchMapping("/{uuid}/{ressource}")
    public ResponseEntity<?> patchEleve(
            @PathVariable String uuid,
            @PathVariable String ressource,
            @Valid @RequestBody UpdateEleve eleve) {
        boolean isUpdated = false;
        if(ressource.equals("soi_meme")){
            isUpdated = service.updateSoiMemeEleve(uuid, eleve);
        }
        else if(ressource.equals("demande_inscription")){
            isUpdated = service.updateDemandeinscriptionEleve(uuid, eleve);
        }

        if(isUpdated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
