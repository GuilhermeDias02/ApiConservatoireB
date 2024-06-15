package fr.efrei.apiConservatoire.controller;

import fr.efrei.apiConservatoire.dto.CreateCours;
import fr.efrei.apiConservatoire.dto.UpdateCours;
import fr.efrei.apiConservatoire.model.Cours;
import fr.efrei.apiConservatoire.service.CoursService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cours")
@RestController
@EnableMethodSecurity
public class CoursController {
    private final CoursService service;

    public CoursController(CoursService service){
        this.service = service;
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping
    public ResponseEntity<List<Cours>> getAllCours(){
        return new ResponseEntity<>(service.findAllCours(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/one/{uuid}")
    public ResponseEntity<Cours> getClasse(@PathVariable String uuid){
        Cours cours = service.findCoursByUuid(uuid);

        if(cours != null){
            return new ResponseEntity<>(service.findCoursByUuid(uuid), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // TODO: recuperer tous les cours d'un eleve et d'un prof
//    @GetMapping("/libelle/{libelle}")
//    public ResponseEntity<List<Cours>> getClasseLibelleLike(@PathVariable String libelle){
//        return new ResponseEntity<>(service.findAllClasseByLibelleLike(libelle), HttpStatus.OK);
//    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping
    public ResponseEntity<Cours> postCours(@Valid @RequestBody CreateCours cours){
        Cours coursACreer = service.createCours(cours);
        return new ResponseEntity<>(coursACreer, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteCours(@PathVariable String uuid){
        boolean isDeleted = service.deleteCours(uuid);
        if(isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{uuid}")
    public ResponseEntity<?> putEleve(@PathVariable String uuid, @Valid @RequestBody UpdateCours cours){
        boolean isUpdated = service.updateCours(uuid, cours);
        if(isUpdated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('Admin')")
    @PatchMapping("/{uuid}/{ressource}")
    public ResponseEntity<?> patchCours(
            @PathVariable String uuid,
            @PathVariable String ressource,
            @Valid @RequestBody UpdateCours cours) {
        boolean isUpdated = false;
        if(ressource.equals("salle")){
            isUpdated = service.updateSalleCours(uuid, cours);
        }
        else if(ressource.equals("prof")){
            isUpdated = service.updateProfCours(uuid, cours);
        }

        if(isUpdated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @PreAuthorize("hasRole('Admin')")
//    @PatchMapping("/{uuid}/{ressource}")
//    public ResponseEntity<?> patchElevesClasse(
//            @PathVariable String uuid,
//            @PathVariable String ressource,
//            @Valid @RequestBody UpdateClasse classe) {
//        boolean isUpdated = false;
//        isUpdated = service.updateElevesClasse(uuid, classe);
//
//        if(isUpdated) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
