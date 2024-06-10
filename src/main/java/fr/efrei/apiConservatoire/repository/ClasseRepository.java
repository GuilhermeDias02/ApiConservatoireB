package fr.efrei.apiConservatoire.repository;

import fr.efrei.apiConservatoire.model.Classe;
import fr.efrei.apiConservatoire.model.Eleve;
import fr.efrei.apiConservatoire.model.Instrument;
import fr.efrei.apiConservatoire.model.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, String> {
    List<Classe> findAll();

    Optional<Classe> findOneByUuid(String uuid);
    Optional<Classe> findOneByEleve(Eleve eleve);
    Optional<Classe> findOneByInstrument(Instrument instrument);
    Optional<Classe> findOneByNiveau(Niveau niveau);

    List<Classe> findByLibelleContains(String libelleLike);

    Classe save(Classe classe);

    void deleteByUuid(String uuid);
}
