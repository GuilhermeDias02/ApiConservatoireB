package fr.efrei.apiConservatoire.service;

import fr.efrei.apiConservatoire.dto.CreateEleve;
import fr.efrei.apiConservatoire.model.Eleve;
import fr.efrei.apiConservatoire.model.Utilisateur;
import fr.efrei.apiConservatoire.repository.EleveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EleveService {
    private final EleveRepository repository;

    @Autowired
    public EleveService(EleveRepository eleveRepository){
        this.repository = eleveRepository;
    }

    public List<Eleve> findAllEleves(){
        return repository.findAll();
    }

    public Optional<Eleve> findAllEleveByUtilisateur(Utilisateur utilisateur){
        return repository.findAllByUtilisateur(utilisateur);
    }

    public Optional<Eleve> findEleveByUuid(String uuid){
        return repository.findOneByUuid(uuid);
    }

    public Eleve createEleve(CreateEleve eleve){
        Eleve eleveACreer = new Eleve(
                eleve.getMom(),
                eleve.getPrenom(),
                eleve.getDate_naissance(),
                eleve.isSoi_meme(),
                eleve.isDemande_inscription(),
                eleve.getUtilisateur()
        );

        return repository.save(eleveACreer);
    }


}
