package fr.efrei.apiConservatoire.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateEleve {
    @NotNull
    private boolean soi_meme = false;

    @NotNull
    private boolean demande_inscription;
}
