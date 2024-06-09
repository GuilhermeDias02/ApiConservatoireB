package fr.efrei.apiConservatoire.repository;

import fr.efrei.apiConservatoire.model.Eleve;
import fr.efrei.apiConservatoire.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EleveRepository extends JpaRepository<Eleve, String> {
    List<Eleve> findAll();

    Optional<Eleve> findAllByUtilisateur(Utilisateur utilisateur);

    Optional<Eleve> findOneByUuid(String uuid);

    Utilisateur save(Utilisateur utilisateur);

    void deleteByUuid(String uuid);
}
