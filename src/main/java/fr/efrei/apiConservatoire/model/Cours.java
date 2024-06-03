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
    @Column(nullable = true)
    private Utilisateur prof;

    @ManyToOne
    @Column(nullable = true)
    private Classe classe;

    @ManyToOne
    @Column(nullable = true)
    private Heure heure;

    @ManyToOne
    @Column(nullable = true)
    private Jour jour;
}
