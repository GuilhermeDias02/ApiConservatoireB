package fr.efrei.apiConservatoire.dto;

import jakarta.persistence.Column;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @Column(nullable = false, unique = true, length = 35)
    private String email;

    @Column(nullable = false)
    private String mdp;
}
