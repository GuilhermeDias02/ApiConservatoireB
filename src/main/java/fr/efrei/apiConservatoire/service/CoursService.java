package fr.efrei.apiConservatoire.service;

import fr.efrei.apiConservatoire.dto.CreateCours;
import fr.efrei.apiConservatoire.dto.UpdateCours;
import fr.efrei.apiConservatoire.model.Cours;
import fr.efrei.apiConservatoire.model.Instrument;
import fr.efrei.apiConservatoire.repository.CoursRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursService {
    private final CoursRepository repository;

    public CoursService(CoursRepository repository){
        this.repository = repository;
    }

    public List<Cours> findAllCours(){
        return repository.findAll();
    }

    public Cours findCoursByUuid(String uuid){
        return repository.findOneByUuid(uuid).orElse(null);
    }

    public Cours createCours(CreateCours cours){
        Cours coursACreer = new Cours(
                cours.getLibelle(),
                cours.getSalle(),
                cours.getProf(),
                cours.getClasse(),
                cours.getHeure(),
                cours.getJour()
        );

        return repository.save(coursACreer);
    }

    @Transactional
    public boolean deleteCours(String uuid){
        Cours coursASupp = findCoursByUuid(uuid);

        if(coursASupp != null){
            repository.deleteByUuid(uuid);
            return true;
        }
        return false;
    }

    public boolean updateCours(String uuid, UpdateCours cours){
        Cours coursAMaj = findCoursByUuid(uuid);

        if(coursAMaj != null){
            coursAMaj.setLibelle(cours.getLibelle());
            coursAMaj.setSalle(cours.getSalle());
            coursAMaj.setProf(cours.getProf());
            coursAMaj.setClasse(cours.getClasse());
            coursAMaj.setHeure(cours.getHeure());
            coursAMaj.setJour(cours.getJour());
            repository.save(coursAMaj);
            return true;
        }
        return false;
    }

    public boolean updateSalleCours(String uuid, UpdateCours cours){
        Cours coursAMaj = findCoursByUuid(uuid);

        if(coursAMaj != null && coursAMaj.getSalle() != null){
            coursAMaj.setSalle(cours.getSalle());
            repository.save(coursAMaj);
            return true;
        }
        return false;
    }

    public boolean updateProfCours(String uuid, UpdateCours cours){
        Cours coursAMaj = findCoursByUuid(uuid);

        if(coursAMaj != null && coursAMaj.getProf() != null){
            coursAMaj.setProf(cours.getProf());
            repository.save(coursAMaj);
            return true;
        }
        return false;
    }
}
