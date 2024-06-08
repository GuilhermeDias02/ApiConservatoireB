package fr.efrei.apiConservatoire.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {
    private String email;

    private String mdp;

    private String nom;

    private String telephone;
}
