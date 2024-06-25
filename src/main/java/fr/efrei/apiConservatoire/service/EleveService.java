package fr.efrei.apiConservatoire.service;

import fr.efrei.apiConservatoire.dto.CreateEleve;
import fr.efrei.apiConservatoire.dto.UpdateEleve;
import fr.efrei.apiConservatoire.model.Eleve;
import fr.efrei.apiConservatoire.model.Utilisateur;
import fr.efrei.apiConservatoire.repository.EleveRepository;
import jakarta.transaction.Transactional;
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

    public List<Eleve> findAllEleveByUtilisateur(Utilisateur utilisateur){
        return repository.findAllByUtilisateur(utilisateur);
    }

    public Eleve findEleveByUuid(String uuid){
        return repository.findOneByUuid(uuid).orElse(null);
    }

    public Eleve createEleve(CreateEleve eleve){
        Eleve eleveACreer = new Eleve(
                eleve.getNom(),
                eleve.getPrenom(),
                eleve.getDate_naissance(),
                eleve.isSoi_meme(),
                eleve.isDemande_inscription(),
                eleve.getUtilisateur()
        );

        return repository.save(eleveACreer);
    }

    @Transactional
    public boolean deleteEleve(String uuid){
        Eleve eleveASupp = findEleveByUuid(uuid);

        if(eleveASupp != null){
            repository.deleteByUuid(uuid);
            return true;
        }
        return false;
    }

    public boolean updateEleve(String uuid, UpdateEleve eleve){
        Eleve eleveAMaj = findEleveByUuid(uuid);

        if(eleveAMaj != null){
            eleveAMaj.setSoi_meme(eleve.isSoi_meme());
            eleveAMaj.setDemande_inscription(eleve.isDemande_inscription());
            repository.save(eleveAMaj);
            return true;
        }
        return false;
    }

    public boolean updateSoiMemeEleve(String uuid, UpdateEleve eleve){
        Eleve eleveAMaj = findEleveByUuid(uuid);

        if(eleveAMaj != null){
            eleveAMaj.setSoi_meme(eleve.isSoi_meme());
            repository.save(eleveAMaj);
            return true;
        }
        return false;
    }

    public boolean updateDemandeinscriptionEleve(String uuid, UpdateEleve eleve){
        Eleve eleveAMaj = findEleveByUuid(uuid);

        if(eleveAMaj != null){
            eleveAMaj.setDemande_inscription(eleve.isDemande_inscription());
            repository.save(eleveAMaj);
            return true;
        }
        return false;
    }
}
