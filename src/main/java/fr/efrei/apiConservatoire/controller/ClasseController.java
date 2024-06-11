package fr.efrei.apiConservatoire.controller;

import fr.efrei.apiConservatoire.dto.CreateClasse;
import fr.efrei.apiConservatoire.dto.UpdateClasse;
import fr.efrei.apiConservatoire.dto.UpdateEleve;
import fr.efrei.apiConservatoire.model.Classe;
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

    // tout le monde peut voir les possibilités d'inscription sur le site mais en front faut filtrer pour pas que tout le monde ait accès à par exemple les élèves déjà inscrits
    // TODO: dto viewClasse qui ne donne ques les infos nécessaires aux utilisateurs, ex liste eleves null
    @GetMapping
    public ResponseEntity<List<Classe>> getAllClasses(){
        return new ResponseEntity<>(service.findAllClasses(), HttpStatus.OK);
    }

    // on pourra récupérer des infos supplémentaires sur une classe précise ex les eleves inscrits
    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/one/{uuid}")
    public ResponseEntity<Classe> getClasse(@PathVariable String uuid){
        Classe classe = service.findClasseByUuid(uuid);

        if(classe != null){
            return new ResponseEntity<>(service.findClasseByUuid(uuid), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // avec la barre de recherche on peut filtrer un certain nombre de classes qui aurait un niveau ou instrument dans le nom de la classe
    @GetMapping("/libelle/{libelle}")
    public ResponseEntity<List<Classe>> getClasseLibelleLike(@PathVariable String libelle){
        return new ResponseEntity<>(service.findAllClasseByLibelleLike(libelle), HttpStatus.OK);
    }

    // on ne peut creer une nouvelle classe que si on est un admin via un menu special
    @PreAuthorize("hasRole('Admin')")
    @PostMapping
    public ResponseEntity<Classe> postClasse(@Valid @RequestBody CreateClasse classe){
        Classe classeACreer = service.createClasse(classe);
        return new ResponseEntity<>(classeACreer, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteEleve(@PathVariable String uuid){
        boolean isDeleted = service.deleteClasse(uuid);
        if(isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{uuid}")
    public ResponseEntity<?> putEleve(@PathVariable String uuid, @Valid @RequestBody UpdateClasse classe){
        boolean isUpdated = service.updateClasse(uuid, classe);
        if(isUpdated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // c'est des bool donc je pourrais prendre un
    @PreAuthorize("hasRole('Admin')")
    @PatchMapping("/{uuid}/{ressource}")
    public ResponseEntity<?> patchClasse(
            @PathVariable String uuid,
            @PathVariable String ressource,
            @Valid @RequestBody UpdateClasse classe) {
        boolean isUpdated = false;
        if(ressource.equals("description")){
            isUpdated = service.updateDescriptionClasse(uuid, classe);
        }
//        else if(ressource.equals("")){
//            isUpdated = service.updateDemandeinscriptionEleve(uuid, eleve);
//        }

        if(isUpdated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('Admin') or hasRole('Parent') or hasRole('Prof')")
    @PatchMapping("/{uuid}/{ressource}")
    public ResponseEntity<?> patchElevesClasse(
            @PathVariable String uuid,
            @PathVariable String ressource,
            @Valid @RequestBody UpdateClasse classe) {
        boolean isUpdated = false;
        isUpdated = service.updateElevesClasse(uuid, classe);

        if(isUpdated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
