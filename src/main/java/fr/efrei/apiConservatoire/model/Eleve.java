package fr.efrei.apiConservatoire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Eleve {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String mom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private Date date_naissance;

    @Column(nullable = false)
    private boolean soi_meme = false;

    @Column(nullable = false)
    private boolean demande_inscription;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Classe classe;

    public Eleve(String nom, String prenom, Date date_naissance, boolean soi_meme, boolean demande_inscription, Utilisateur utilisateur){
        this.mom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.soi_meme = soi_meme;
        this.demande_inscription = demande_inscription;
        this.utilisateur = utilisateur;
    }
}
