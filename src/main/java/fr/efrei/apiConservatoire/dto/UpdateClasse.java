package fr.efrei.apiConservatoire.dto;

import fr.efrei.apiConservatoire.model.Eleve;
import fr.efrei.apiConservatoire.model.Instrument;
import fr.efrei.apiConservatoire.model.Niveau;
import lombok.Data;

import java.util.List;

@Data
public class UpdateClasse {
    private String libelle;

    private String description;

    private List<Eleve> eleves;

    private Instrument instrument;

    private Niveau niveau;
}
