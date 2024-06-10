package fr.efrei.apiConservatoire.service;

import fr.efrei.apiConservatoire.dto.CreateClasse;
import fr.efrei.apiConservatoire.dto.UpdateClasse;
import fr.efrei.apiConservatoire.dto.UpdateEleve;
import fr.efrei.apiConservatoire.model.Classe;
import fr.efrei.apiConservatoire.model.Eleve;
import fr.efrei.apiConservatoire.model.Instrument;
import fr.efrei.apiConservatoire.repository.ClasseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasseService {
    private final ClasseRepository repository;

    @Autowired
    public ClasseService(ClasseRepository repository){
        this.repository = repository;
    }

    public List<Classe> findAllClasses(){
        return repository.findAll();
    }

    public List<Classe> findAllClasseByLibelleLike(String libelleLike){
        return repository.findByLibelleContains(libelleLike);
    }

    public List<Classe> findAllClasseByInstrument(Instrument instrument){
        return repository.findAllByInstrument(instrument);
    }

    public Classe findClasseByUuid(String uuid){
        return repository.findOneByUuid(uuid).orElse(null);
    }

    public Classe createClasse(CreateClasse classe){
        Classe classeACreer = new Classe(
            classe.getLibelle(),
            classe.getDescription(),
            classe.getEleves(),
            classe.getInstrument(),
            classe.getNiveau()
        );

        return repository.save(classeACreer);
    }

    @Transactional
    public boolean deleteClasse(String uuid){
        Classe classeASupp = findClasseByUuid(uuid);

        if(classeASupp != null){
            repository.deleteByUuid(uuid);
            return true;
        }
        return false;
    }

    public boolean updateClasse(String uuid, UpdateClasse classe){
        Classe classeAMaj = findClasseByUuid(uuid);

        if(classeAMaj != null){
            classeAMaj.setSoi_meme(eleve.isSoi_meme());
            classeAMaj.setDemande_inscription(eleve.isDemande_inscription());
            repository.save(classeAMaj);
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
