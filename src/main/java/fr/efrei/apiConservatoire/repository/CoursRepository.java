package fr.efrei.apiConservatoire.repository;

import fr.efrei.apiConservatoire.model.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoursRepository extends JpaRepository<Cours, String> {
    List<Cours> findAll();

    Optional<Cours> findOneByUuid(String uuid);

    Cours save(Cours cours);

    void deleteByUuid(String uuid);
}
