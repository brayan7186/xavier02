package edu.xavier.DAWI_CL2.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer filmId;
    private String title;
    private String description;
    private Integer releaseYear;
    private Integer originalLanguageId;
    private Integer rentalDuration;
    private Double rentalRate;
    private Integer length;
    private Double replacementCost;
    private String rating;
    private String specialFeatures;
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;


    @OneToMany(mappedBy = "film", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<FilmActor> filmActors;
    @OneToMany(mappedBy = "film", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<FilmCategory> filmCategories;
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inventory> inventories;

}
