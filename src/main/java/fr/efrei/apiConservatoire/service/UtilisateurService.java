package fr.efrei.apiConservatoire.service;

import fr.efrei.apiConservatoire.model.Utilisateur;
import fr.efrei.apiConservatoire.repository.UtilisateurRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.String;

@Data
@Service
public class UtilisateurService {
    private final UtilisateurRepository repository;

    @Autowired
    public UtilisateurService(UtilisateurRepository repository){
        this.repository = repository;
    }

    public Utilisateur findUtilisateurByUuid(String uuid){
        return repository.findByUuid(uuid).orElse(null);
    }

    public Utilisateur findUtilisateurByEmail(String email){
        return repository.findByEmail(email).orElse(null);
    }
}
