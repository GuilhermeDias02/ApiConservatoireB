package fr.efrei.apiConservatoire.repository;

import fr.efrei.apiConservatoire.model.Eleve;
import fr.efrei.apiConservatoire.model.Utilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, String> {
    Optional<Utilisateur> findByEmail(String email);

    Optional<Utilisateur> findByUuid(String uuid);
}
