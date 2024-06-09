package fr.efrei.apiConservatoire.dto;

import fr.efrei.apiConservatoire.model.Utilisateur;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class CreateEleve {
    @NotBlank
    private String mom;

    @NotBlank
    private String prenom;

    @NotNull
    private Date date_naissance;

    @NotNull
    private boolean soi_meme = false;

    @NotNull
    private boolean demande_inscription;

    @NotNull
    private Utilisateur utilisateur;
}
