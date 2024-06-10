package fr.efrei.apiConservatoire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = true)
    private String description;

    @OneToMany
    @Column(nullable = true)
    private List<Eleve> eleves;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Instrument instrument;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Niveau niveau;

    public Classe(String libelle, String description, List<Eleve> eleves, Instrument instrument, Niveau niveau){
        this.libelle = libelle;
        this.description = description;
        this.eleves = eleves;
        this.instrument = instrument;
        this.niveau = niveau;
    }
}
