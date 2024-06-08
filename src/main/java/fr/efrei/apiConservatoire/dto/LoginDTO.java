package fr.efrei.apiConservatoire.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @Column(nullable = false, unique = true, length = 35)
    private String email;

    @Column(nullable = false)
    private String mdp;
}
