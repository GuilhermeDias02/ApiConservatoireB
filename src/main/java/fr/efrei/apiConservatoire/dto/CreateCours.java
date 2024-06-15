package fr.efrei.apiConservatoire.dto;

import fr.efrei.apiConservatoire.model.Classe;
import fr.efrei.apiConservatoire.model.Heure;
import fr.efrei.apiConservatoire.model.Jour;
import fr.efrei.apiConservatoire.model.Utilisateur;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateCours {
    private String libelle;

    private String salle;

    private Utilisateur prof;

    private Classe classe;

    private Heure heure;

    private Jour jour;
}
