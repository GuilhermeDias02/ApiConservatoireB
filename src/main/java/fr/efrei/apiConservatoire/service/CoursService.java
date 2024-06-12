package fr.efrei.apiConservatoire.service;

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

    public List<Cours> findAllClasses(){
        return repository.findAll();
    }

    public Cours findCoursByUuid(String uuid){
        return repository.findOneByUuid(uuid).orElse(null);
    }

    public Cours createClasse(CreateCours classe){
        Cours coursACreer = new Cours(
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
            classeAMaj.setLibelle(classe.getLibelle());
            classeAMaj.setDescription(classe.getDescription());
            classeAMaj.setEleves(classe.getEleves());
            classeAMaj.setInstrument(classe.getInstrument());
            classeAMaj.setNiveau(classe.getNiveau());
            repository.save(classeAMaj);
            return true;
        }
        return false;
    }

    public boolean updateElevesClasse(String uuid, UpdateClasse classe){
        Classe classeAMaj = findClasseByUuid(uuid);

        if(classeAMaj != null && classeAMaj.getEleves() != null){
            classeAMaj.setEleves(classe.getEleves());
            repository.save(classeAMaj);
            return true;
        }
        return false;
    }

    public boolean updateDescriptionClasse(String uuid, UpdateClasse classe){
        Classe classeAMaj = findClasseByUuid(uuid);

        if(classeAMaj != null && classeAMaj.getDescription() != null){
            classeAMaj.setDescription(classe.getDescription());
            repository.save(classeAMaj);
            return true;
        }
        return false;
    }
}
