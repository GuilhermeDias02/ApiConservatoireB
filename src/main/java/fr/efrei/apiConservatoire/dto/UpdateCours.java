package fr.efrei.apiConservatoire.dto;

import fr.efrei.apiConservatoire.model.Classe;
import fr.efrei.apiConservatoire.model.Heure;
import fr.efrei.apiConservatoire.model.Jour;
import fr.efrei.apiConservatoire.model.Utilisateur;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCours {
    @NotBlank
    private String libelle;

    @NotBlank
    private String salle;

    private Utilisateur prof;

    private Classe classe;

    private Heure heure;

    private Jour jour;
}
