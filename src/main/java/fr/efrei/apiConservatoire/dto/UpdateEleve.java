package fr.efrei.apiConservatoire.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateEleve {
    @NotNull
    private boolean soi_meme = false;

    @NotNull
    private boolean demande_inscription;
}
