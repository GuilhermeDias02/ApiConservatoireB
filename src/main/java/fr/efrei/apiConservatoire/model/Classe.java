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

    @OneToMany
    @Column(nullable = true)
    private List<Eleve> eleves;

    @ManyToOne
    @Column(nullable = true)
    private Instrument instrument;

    @ManyToOne
    @Column(nullable = false)
    private Niveau niveau;
}
