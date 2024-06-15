package fr.efrei.apiConservatoire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false)
    private String salle;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Utilisateur prof;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Classe classe;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Heure heure;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Jour jour;

    public Cours(String libelle, String salle, Utilisateur prof, Classe classe, Heure heure, Jour jour){
        this.libelle = libelle;
        this.salle = salle;
        this.prof = prof;
        this.classe = classe;
        this.heure = heure;
        this.jour = jour;
    }
}
