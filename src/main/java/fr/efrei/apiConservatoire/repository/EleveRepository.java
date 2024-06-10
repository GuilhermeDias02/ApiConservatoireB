package fr.efrei.apiConservatoire.repository;

import fr.efrei.apiConservatoire.model.Classe;
import fr.efrei.apiConservatoire.model.Eleve;
import fr.efrei.apiConservatoire.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EleveRepository extends JpaRepository<Eleve, String> {
    List<Eleve> findAll();

    List<Eleve> findAllByUtilisateur(Utilisateur utilisateur);
    List<Eleve> findAllByClasse(Classe classe);

    Optional<Eleve> findOneByUuid(String uuid);

    Utilisateur save(Utilisateur utilisateur);

    void deleteByUuid(String uuid);
}
