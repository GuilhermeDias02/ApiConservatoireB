package fr.efrei.apiConservatoire.dto;

import fr.efrei.apiConservatoire.model.Eleve;
import fr.efrei.apiConservatoire.model.Instrument;
import fr.efrei.apiConservatoire.model.Niveau;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateClasse {
    @NotBlank
    private String libelle;

    @NotBlank
    private String description;

    private List<Eleve> eleves;

    private Instrument instrument;

    private Niveau niveau;
}
