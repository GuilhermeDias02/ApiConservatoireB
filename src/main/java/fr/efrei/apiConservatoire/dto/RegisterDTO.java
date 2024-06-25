package fr.efrei.apiConservatoire.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @NotBlank
    private String adresse;

    @NotBlank
    private String telephone;

    @NotBlank
    private String email;

    @NotBlank
    private String mdp;
}
